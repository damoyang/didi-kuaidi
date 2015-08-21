package com.ddhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;
import com.ddhy.domain.*;


/**
 * User���JPA Repository
 *
 * @author 灏�缈�
 * @version 1.0.0
 */
public interface CommonCartypeRepository extends JpaRepository<YybCommonCartype,Integer>{
	//根据货物吨位获取车的油耗 用来概算路费
	@Query("select p from YybCommonCartype p where yybWeight > ?1 order by yybWeight")
	List<YybCommonCartype> findCarByWeight(Integer weight);
}