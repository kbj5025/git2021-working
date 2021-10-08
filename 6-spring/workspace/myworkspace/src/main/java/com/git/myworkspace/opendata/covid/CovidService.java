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

	// 시군구별 covid 시간단위 조회
	// 1시간마다 실행(js, setInterval)
	// fixedRate : 가장 처음에 실행되고 간격별로 실행됨
	@Scheduled(cron = "0 30 * * * *")

	// @CacheEvict(value="캐시이름", allEntries = true): 해당 캐시이름의 모든 키를 삭제
	@CacheEvict(value = "covid-current", allEntries = true)
	public void requestCovid() throws IOException {

	}

	@SuppressWarnings("deprecation")
	// @Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	public void requestCovidSigunguHour() throws IOException {
		System.out.println(new Date().toLocaleString());

		/* 데이터 요청하고 xml 받아오기 시작 */
		// StringBuilder 문자열을 빌더방식으로 생성하는 클래스
		// 1. 요청 url 만들기
		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.data.go.kr/openapi/service/rest"); // 호스트/게이트웨이
		builder.append("/Covid19"); // 서비스
		builder.append("/getCovid19SidoInfStateJson"); // 기능
		builder.append("?serviceKey=" + SERVICE_KEY);
		builder.append("&startCreateDt=20210923");
		builder.append("&endCreateDt=20200410");
		builder.append("&pageNo=1&numOfRows=10");

		System.out.println(builder.toString());

		// 2. url 객체 생성
		URL url = new URL(builder.toString());

		// 3. http 접속 생성
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[]배열로 데이터를 읽어옴
		byte[] result = con.getInputStream().readAllBytes();

		// 5. 문자열 변환
		String data = new String(result, "UTF-8");
		// System.out.println(data);

		// http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=NC0Daw6hqFP8%2FOL7GLbX7VMTdg6aW1TQ%2B9c5%2BnbpO4ZOvJS57B%2FCz4KFAkCEzNJY2%2FyhxLMR112G7HSQv4K%2Fow%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200410&endCreateDt=20200410
		/* 데이터 요청하고 xml 받아오기 끝 */

		/* xml -> json -> object(java) 시작 */
		// xml(문자열) -> json(객체)
		JSONObject jsonObj = XML.toJSONObject(data);
		// json(객체) -> xml(문자열)
		String json = jsonObj.toString(2);
		// System.out.println(json);

		/* xml -> json -> object(java) 끝 */
		// json(문자열) -> java(object)
		CovidSigunguHourResponse response = new Gson().fromJson(json, CovidSigunguHourResponse.class);
		System.out.println(response);

		/* 응답 객체 -> 엔티티 시작 */
		List<CovidSigunguHour> list = new ArrayList<CovidSigunguHour>();

		for (CovidSigunguHourResponse.Item item : response.getResponse().getBody().getItems().getItem()) {

			CovidSigunguHour record = CovidSigunguHour.builder().guBun(item.getGubun()).defCnt(item.getDefCnt())
					.isolIngCnt(item.getIsolIngCnt()).pm10Value(item.getPm10Value()).overFlowCnt(item.getOverFlowCnt())
					.localOccCnt(item.getLocalOccCnt()).stdDay(item.getStdDay()).updateDt(item.getUpdateDt())
					.isolClearCnt(item.getIsolClearCnt()).incDec(item.getIncDec()).deathCnt(item.getDeathCnt())
					.createDt(item.getCreateDt()).seq(item.getSeq()).build();

			list.add(record);
			/* 응답 객체 -> 엔티티 끝 */
		}
		/* 엔티티 객체 -> 리포지터리로 저장 시작 */
		repo.saveAll(list);
		/* 엔티티 객체 -> 리포지터리로 저장 끝 */
	}

}