package com.ddhy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ddhy.domain.YybBussOrder;


/**
 * User JPA Repository
 *
 * @author
 * @version 1.0.0
 */
public interface OrderRepository extends JpaRepository<YybBussOrder,Integer>{//JpaRepository<YybBussOrder,Integer>{
	@Query("select o from YybBussOrder o where yybOrderno=?1")
	YybBussOrder findByOrderNo(String orderNo);
	@Query("select o from YybBussOrder o where yybOrderstatus=?1")
	List<YybBussOrder> findByOrderStatus(String status);
	@Query("select o from YybBussOrder o where yybOrderstatus=?1")
	List<YybBussOrder> findByOrderStatusByPage(String status, Pageable pageRequest);
	@Query("select count(o) from YybBussOrder o where yybOrderstatus=?1")
	int countSendOrder(String status);
	@Query("select o from YybBussOrder o where yybDriverid=?1")
	List<YybBussOrder> findByDriverId(Integer id);
	@Query("select o from YybBussOrder o where yybDriverid=?1")
	List<YybBussOrder> findByDriverIdPages(Integer id, Pageable pageRequest);
	@Query("select count(o) from YybBussOrder o where yybDriverid=?1")
	int countByDriverIdPages(Integer id);
	
	@Query("select o from YybBussOrder o where yybUserid=?1")
	List<YybBussOrder> findByUserId(Integer id);
	@Query("select o from YybBussOrder o where yybUserid=?1")
	List<YybBussOrder> findByUserIdPages(Integer id, Pageable pageRequest);
	@Query("select count(o) from YybBussOrder o where yybUserid=?1")
	int countByUserIdPages(Integer id);
	
	
	
}