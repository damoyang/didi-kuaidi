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
public interface CustomerBankRepository extends JpaRepository<YybUserBank,Integer>{
	@Query("select b from YybUserBank b where yybUserId=?1")
	List<YybUserBank> findByUserId(Integer id);
	
}