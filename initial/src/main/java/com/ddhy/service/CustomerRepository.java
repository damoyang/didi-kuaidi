package com.ddhy.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ddhy.domain.*;


/**
 * User���JPA Repository
 *
 * @author 灏�缈�
 * @version 1.0.0
 */
public interface CustomerRepository extends JpaRepository<YybUserAccount,Integer>{
	@Query("select c from YybUserAccount c where c.yybPhone=?1")
	public YybUserAccount findByName(String name);
	
}