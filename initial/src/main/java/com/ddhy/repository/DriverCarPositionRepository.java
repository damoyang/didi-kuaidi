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
public interface DriverCarPositionRepository extends JpaRepository<YybDriverPostion,Integer>{
	@Query("select p from YybDriverPostion p where yybDriverid=?1")
	YybDriverPostion findByUserId(Integer id);
	
}