package com.manoecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

	@Query("FROM Orders o WHERE o.user.userId = :userId AND o.orderStatus IN ('PLACED', 'CONFIRMED', 'SHIPPED', 'DELIVERED')")
	public List<Orders> getUserOrders(@Param("userId") Long userId);

}
