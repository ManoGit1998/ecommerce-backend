package com.manoecommerce.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.CartItem;
import com.manoecommerce.entity.Product;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.CardItemException;
import com.manoecommerce.exception.UserException;
import com.manoecommerce.repository.CartItemRepository;
import com.manoecommerce.repository.CartRepository;
import com.manoecommerce.service.CartItemService;
import com.manoecommerce.service.UserService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository cartItemRepo;

	@Autowired
	UserService userService;

	@Autowired
	CartRepository cartRepo;

	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

		CartItem createdCartItem = cartItemRepo.save(cartItem);
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CardItemException, UserException {
		CartItem item = findCartItemById(id);
		User user = userService.finduserById(item.getUserId());
		if (user.getUserId().equals(userId)) {
			item.setQuantity(item.getQuantity());
			item.setPrice(item.getQuantity() * item.getProduct().getPrice());
			item.setDiscountedPrice(item.getQuantity() * item.getProduct().getDiscountedPrice());
		}

		return cartItemRepo.save(item);
	}

	@Override
	public CartItem isCartitemExist(Cart cart, Product product, String size, Long userId) {

		CartItem cartItem = cartItemRepo.isCartItemExist(cart, product, size, userId);

		return cartItem;
	}

	@Override
	public void removeCardItem(Long userId, Long cartItemId) throws CardItemException, UserException {

		CartItem item = findCartItemById(cartItemId);
		User user = userService.finduserById(item.getUserId());
		User reqUser = userService.finduserById(userId);
		if (user.getUserId().equals(reqUser.getUserId())) {
			cartItemRepo.deleteById(cartItemId);
		} else {
			throw new UserException("You cannot remove another user items");
		}
	}

	@Override
	public CartItem findCartItemById(Long cartitemId) throws CardItemException {

		Optional<CartItem> cartObj = cartItemRepo.findById(cartitemId);
		if (cartObj.isPresent()) {
			return cartObj.get();
		}

		throw new CardItemException("Cartitem not found with id : " + cartitemId);
	}

}
