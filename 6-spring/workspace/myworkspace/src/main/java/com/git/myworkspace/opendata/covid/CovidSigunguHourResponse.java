package com.git.myworkspace.opendata.covid;

import java.util.List;

import lombok.Data;

@Data

public class CovidSigunguHourResponse {

	private Response response;

	@Data
	public class Response {
		private Header header;
		private Body body;
	}

	@Data
	public class Header {
		private String resultCode;
		private String resultMsg;
	}

	@Data
	public class Body {
		private Items items;
	}

	@Data
	public class Items {
		private List<Item> item;
	}

	@Data
	public class Item {
		// OLAP Cube 형식으로 데이터
		private String gubun;
		private String defCnt;
		private String isolIngCnt;
		private String pm10Value;
		private String overFlowCnt;
		private String localOccCnt;
		private String stdDay;
		private String updateDt;
		private String isolClearCnt;
		private String incDec;
		private String deathCnt;
		private String createDt;
		private String seq;

	}
}
