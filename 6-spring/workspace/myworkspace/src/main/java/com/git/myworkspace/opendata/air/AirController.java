package com.git.myworkspace.opendata.air;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component("airController")
@RestController
@RequestMapping(value = "/opendata/air")
public class AirController {
	private AirSigunguHourRepository repo;
	private final String cachName = "air-current";

	@Autowired
	public AirController(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// 시도명으로 시도 하위에 시군군의 현재 데이터를 조회
	// 예) 서울 -> 강남 ... 중랑구 현재 데이터를 조회

	// 최근 25개의 데이터를 조회
	@Cacheable(value = "cachName", key = "'all'")
	@GetMapping(value = "/sido/current")
	public List<AirSigunguHour> getAirSidoCurrent() {

		// 여러개의 필드로 정렬
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "dataTime"));
		orders.add(new Order(Sort.Direction.ASC, "cityName"));
		return repo.findAll(PageRequest.of(0, 25, Sort.by("orders"))).toList();
	}

	@Cacheable(value = cachName, key = "#city")
	@GetMapping(value = "/sido/current/{city}")
	public List<AirSigunguHour> getAirSidoCurrent(@PathVariable String city) {
		Pageable page = PageRequest.of(0, 12, Sort.by("dataTime").descending());
		return repo.findByCityName(page, city);
	}
}
