package com.git.myworkspace.opendata.air;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

//���� ������Ʈ
//1. �ܺ� �ý��� ���
//2. ������ Ʈ����� ó��
@Service
public class AirService {

	private final String SERVICE_KEY = "NC0Daw6hqFP8%2FOL7GLbX7VMTdg6aW1TQ%2B9c5%2BnbpO4ZOvJS57B%2FCz4KFAkCEzNJY2%2FyhxLMR112G7HSQv4K%2Fow%3D%3D";

	private AirSigunguHourRepository repo;

	@Autowired
	public AirService(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// �ñ����� ����� �ð����� ��ȸ
	// 1�ð����� ����(js, setInterval)
	// fixedRate : ���� ó���� ����ǰ� ���ݺ��� �����
	@Scheduled(cron = "0 30 * * * *")
	// @CacheEvict(value="ĳ���̸�", allEntries = true): �ش� ĳ���̸��� ��� Ű�� ����
	@CacheEvict(value = "air-current", allEntries = true)
	public void requestAir() throws IOException {
		String[] sidoNames = { "����" };
		for (String sidoName : sidoNames) {
			requestAirSigunguHour(sidoName);
		}
	}

	@SuppressWarnings("deprecation")
	// @Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	public void requestAirSigunguHour(String sido) throws IOException {
		System.out.println(new Date().toLocaleString());

		/* ������ ��û�ϰ� xml �޾ƿ��� ���� */
		// StringBuilder ���ڿ��� ����������� �����ϴ� Ŭ����
		// 1. ��û url �����
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584"); // ȣ��Ʈ/����Ʈ����
		builder.append("/ArpltnStatsSvc"); // ����
		builder.append("/getCtprvnMesureSidoLIst"); // ���(�õ�-�ñ�������ȸ)
		builder.append("?sidoName=" + URLEncoder.encode("����", "UTF-8")); // ���︸
		builder.append("&searchCondition=HOUR"); // 1�ð�����
		builder.append("&pageNo=1&numOfRows=25"); // ������ �� 25��
		builder.append("&serviceKey=" + SERVICE_KEY);

		System.out.println(builder.toString());

		// 2. url ��ü ����
		URL url = new URL(builder.toString());

		// 3. Http ���� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[]�迭�� �����͸� �о��
		byte[] result = con.getInputStream().readAllBytes();

		// 5. byte[] -> ���ڿ�(xml) ��ȯ
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?sidoName=����&searchCondition=HOUR&pageNo=1&numOfRows=25&serviceKey=NC0Daw6hqFP8%2FOL7GLbX7VMTdg6aW1TQ%2B9c5%2BnbpO4ZOvJS57B%2FCz4KFAkCEzNJY2%2FyhxLMR112G7HSQv4K%2Fow%3D%3D
		/* ������ ��û�ϰ� xml �޾ƿ��� �� */

		/* xml -> json -> object(java) ���� */
		// xml(���ڿ�) -> json(��ü)
		JSONObject jsonObj = XML.toJSONObject(data);
		// json(��ü) -> xml(���ڿ�)
		String json = jsonObj.toString(2);
		// System.out.println(json);

		// json(���ڿ�) -> java(object)
		AirSigunguHourResponse response = new Gson().fromJson(json, AirSigunguHourResponse.class);
		System.out.println(response);
		/* xml -> json -> object(java) �� */

		/* ���� ��ü -> ��ƼƼ ���� */
		List<AirSigunguHour> list = new ArrayList<AirSigunguHour>();
		for (AirSigunguHourResponse.Item item : response.getResponse().getBody().getItems().getItem()) {
			AirSigunguHour record = AirSigunguHour.builder().dataTime(item.getDataTime()).sidoName(item.getSidoName())
					.cityName(item.getCityName()).pm10Value(item.getPm10Value()).pm25Value(item.getPm25Value())
					.coValue(item.getCoValue()).so2Value(item.getSo2Value()).o3Value(item.getO3Value())
					.no2Value(item.getNo2Value()).build();
			list.add(record);
			/* ���� ��ü -> ��ƼƼ �� */
		}
		/* ��ƼƼ ��ü -> �������͸��� ���� ���� */
		repo.saveAll(list);
		/* ��ƼƼ ��ü -> �������͸��� ���� �� */
	}
}
