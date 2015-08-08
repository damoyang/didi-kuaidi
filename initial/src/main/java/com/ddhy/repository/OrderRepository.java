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
public interface OrderRepository extends JpaRepository<YybBussOrder,Integer>{
	@Query("select o from YybBussOrder o where yybOrderno=?1")
	YybBussOrder findByOrderNo(String orderNo);
	
}