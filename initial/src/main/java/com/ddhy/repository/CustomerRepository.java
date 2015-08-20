package com.ddhy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ddhy.domain.*;


/**
 */
public interface CustomerRepository extends JpaRepository<YybUserAccount,Integer>{
	@Query("select c from YybUserAccount c where c.yybPhone=?1")
	public YybUserAccount findByName(String name);
	
	@Query("select d from YybUserAccount d where d.yybToken=?1")
	public List<YybUserAccount> checkToken(String token);
	
}