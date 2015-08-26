package com.ddhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

import com.ddhy.domain.YybDriverCar;
import com.ddhy.domain.YybSysBasicinfo;

public interface DriverCarRepository extends JpaRepository<YybDriverCar, Integer>{	
	@Query("select d from YybDriverCar d where yybDriverid=?1")
	List<YybDriverCar> findByDriverId(Integer id);
	
	@Query("select d from YybDriverCar d where yybCarlicense=?1")
	List<YybDriverCar> findByCarlicense(String carlicense);

}
