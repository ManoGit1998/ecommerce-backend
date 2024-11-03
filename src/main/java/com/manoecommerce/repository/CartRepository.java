package com.manoecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query("from Cart c where c.user.userId=:userId")
	Cart findByUserId(Long userId);

}
