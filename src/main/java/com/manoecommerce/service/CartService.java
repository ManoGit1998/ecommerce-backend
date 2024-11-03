package com.manoecommerce.service;

import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.request.AddItemRequest;

@Service
public interface CartService {

	public Cart createCard(User user);

	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
