package com.git.myworkspace.opendata.covid;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(indexes = @Index(name = "idx_covid_sigungu_hour_1", columnList = "gubun"))
public class CovidSigunguHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String guBun;
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
