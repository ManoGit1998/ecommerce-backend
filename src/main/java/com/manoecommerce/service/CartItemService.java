package com.manoecommerce.service;

import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.CartItem;
import com.manoecommerce.entity.Product;
import com.manoecommerce.exception.CardItemException;
import com.manoecommerce.exception.UserException;

@Service
public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);

	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CardItemException, UserException;

	public CartItem isCartitemExist(Cart cart, Product product, String size, Long userId);

	public void removeCardItem(Long userId, Long cartItemId) throws CardItemException, UserException;

	public CartItem findCartItemById(Long cartitemId) throws CardItemException;

}
