package com.ddhy.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

import com.ddhy.domain.YybSysBasicinfo;

public interface SysBasicinfoRepository extends JpaRepository<YybSysBasicinfo, Integer>{
	@Query("select s from YybSysBasicinfo s where s.yybResname=?1")
	List<YybSysBasicinfo> findByName(String name);
	

}
