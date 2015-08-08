package com.ddhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ddhy.domain.*;


/**
 */
public interface CustomerRepository extends JpaRepository<YybUserAccount,Integer>{
	@Query("select c from YybUserAccount c where c.yybPhone=?1")
	public YybUserAccount findByName(String name);
	
}