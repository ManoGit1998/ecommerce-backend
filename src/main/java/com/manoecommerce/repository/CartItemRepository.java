package com.manoecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.CartItem;
import com.manoecommerce.entity.Product;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	
	@Query("SELECT c FROM CartItem c where c.cart=:cart AND c.product=:product AND c.size=:size AND c.userId=:userId")
	CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

	
	
}
