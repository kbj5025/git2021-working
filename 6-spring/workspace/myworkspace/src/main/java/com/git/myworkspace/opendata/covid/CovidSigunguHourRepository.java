package com.git.myworkspace.opendata.covid;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidSigunguHourRepository extends JpaRepository<CovidSigunguHour, Long> {
	List<CovidSigunguHour> findByGuBun(Pageable page, String city);
}
