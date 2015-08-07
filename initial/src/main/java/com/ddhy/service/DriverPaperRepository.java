package com.ddhy.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

import com.ddhy.domain.YybDriverCar;
import com.ddhy.domain.YybDriverPaper;
import com.ddhy.domain.YybSysBasicinfo;

public interface DriverPaperRepository extends JpaRepository<YybDriverPaper, Integer>{
	@Query("select p from YybDriverPaper p where p.yybDriverid=?1")
	List<YybDriverPaper> findByDriverId(Integer id);
	

}
