package com.git.myworkspace.opendata.covid;

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

@Component("covidController")
@RestController
@RequestMapping(value = "/opendata/covid")
public class CovidController {
	private CovidSigunguHourRepository repo;
	private final String cachName = "covid-current";

	@Autowired
	public CovidController(CovidSigunguHourRepository repo) {
		this.repo = repo;
	}

	@Cacheable(value = "cachName", key = "'all'")
	@GetMapping(value = "/sido/current")
	public List<CovidSigunguHour> getCovidSidoCurrent() {

		// 여러개의 필드로 정렬
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "std_day"));
		orders.add(new Order(Sort.Direction.ASC, "gubun"));
		return repo.findAll(PageRequest.of(0, 19, Sort.by("orders").descending())).toList();
	}

	@Cacheable(value = cachName, key = "#city")
	@GetMapping(value = "/sido/current/{gubun}")
	public List<CovidSigunguHour> getCovidSidoCurrent(@PathVariable String city) {
		Pageable page = PageRequest.of(0, 7, Sort.by("std_day").descending());
		return repo.findByGuBun(page, city);
	}
}
