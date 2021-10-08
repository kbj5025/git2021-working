package com.git.myworkspace.opendata.covid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

@Service
public class CovidService {

	private final String SERVICE_KEY = "NC0Daw6hqFP8%2FOL7GLbX7VMTdg6aW1TQ%2B9c5%2BnbpO4ZOvJS57B%2FCz4KFAkCEzNJY2%2FyhxLMR112G7HSQv4K%2Fow%3D%3D";

	private CovidSigunguHourRepository repo;

	@Autowired
	public CovidService(CovidSigunguHourRepository repo) {
		this.repo = repo;
	}

	// �ñ����� covid �ð����� ��ȸ
	// 1�ð����� ����(js, setInterval)
	// fixedRate : ���� ó���� ����ǰ� ���ݺ��� �����
	@Scheduled(cron = "0 30 * * * *")

	// @CacheEvict(value="ĳ���̸�", allEntries = true): �ش� ĳ���̸��� ��� Ű�� ����
	@CacheEvict(value = "covid-current", allEntries = true)
	public void requestCovid() throws IOException {

	}

	@SuppressWarnings("deprecation")
	// @Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	public void requestCovidSigunguHour() throws IOException {
		System.out.println(new Date().toLocaleString());

		/* ������ ��û�ϰ� xml �޾ƿ��� ���� */
		// StringBuilder ���ڿ��� ����������� �����ϴ� Ŭ����
		// 1. ��û url �����
		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.data.go.kr/openapi/service/rest"); // ȣ��Ʈ/����Ʈ����
		builder.append("/Covid19"); // ����
		builder.append("/getCovid19SidoInfStateJson"); // ���
		builder.append("?serviceKey=" + SERVICE_KEY);
		builder.append("&startCreateDt=20210923");
		builder.append("&endCreateDt=20200410");
		builder.append("&pageNo=1&numOfRows=10");

		System.out.println(builder.toString());

		// 2. url ��ü ����
		URL url = new URL(builder.toString());

		// 3. http ���� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[]�迭�� �����͸� �о��
		byte[] result = con.getInputStream().readAllBytes();

		// 5. ���ڿ� ��ȯ
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=NC0Daw6hqFP8%2FOL7GLbX7VMTdg6aW1TQ%2B9c5%2BnbpO4ZOvJS57B%2FCz4KFAkCEzNJY2%2FyhxLMR112G7HSQv4K%2Fow%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200410&endCreateDt=20200410
		/* ������ ��û�ϰ� xml �޾ƿ��� �� */

		/* xml -> json -> object(java) ���� */
		// xml(���ڿ�) -> json(��ü)
		JSONObject jsonObj = XML.toJSONObject(data);
		// json(��ü) -> xml(���ڿ�)
		String json = jsonObj.toString(2);
		// System.out.println(json);

		/* xml -> json -> object(java) �� */
		// json(���ڿ�) -> java(object)
		CovidSigunguHourResponse response = new Gson().fromJson(json, CovidSigunguHourResponse.class);
		System.out.println(response);

		/* ���� ��ü -> ��ƼƼ ���� */
		List<CovidSigunguHour> list = new ArrayList<CovidSigunguHour>();

		for (CovidSigunguHourResponse.Item item : response.getResponse().getBody().getItems().getItem()) {

			CovidSigunguHour record = CovidSigunguHour.builder().guBun(item.getGubun()).defCnt(item.getDefCnt())
					.isolIngCnt(item.getIsolIngCnt()).pm10Value(item.getPm10Value()).overFlowCnt(item.getOverFlowCnt())
					.localOccCnt(item.getLocalOccCnt()).stdDay(item.getStdDay()).updateDt(item.getUpdateDt())
					.isolClearCnt(item.getIsolClearCnt()).incDec(item.getIncDec()).deathCnt(item.getDeathCnt())
					.createDt(item.getCreateDt()).seq(item.getSeq()).build();

			list.add(record);
			/* ���� ��ü -> ��ƼƼ �� */
		}
		/* ��ƼƼ ��ü -> �������͸��� ���� ���� */
		repo.saveAll(list);
		/* ��ƼƼ ��ü -> �������͸��� ���� �� */
	}

}