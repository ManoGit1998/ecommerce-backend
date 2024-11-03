package com.manoecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.CartItem;
import com.manoecommerce.entity.Product;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.repository.CartRepository;
import com.manoecommerce.request.AddItemRequest;
import com.manoecommerce.service.CartItemService;
import com.manoecommerce.service.CartService;
import com.manoecommerce.service.ProductService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartrepo;

	@Autowired
	CartItemService cartItemSerice;

	@Autowired
	ProductService productService;

	@Override
	public Cart createCard(User user) {
		Cart cart = new Cart();
		cart.setUser(user);

		return cartrepo.save(cart);

	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {

		Cart cart = cartrepo.findByUserId(userId);

		Product product = productService.findProductById(req.getProductId());

		CartItem cartItemIsPresent = cartItemSerice.isCartitemExist(cart, product, req.getSize(), userId);

		if (cartItemIsPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			cartItem.setPrice(req.getQuantity() * product.getDiscountedPercent());
			cartItem.setSize(req.getSize());

			CartItem createdCartItem = cartItemSerice.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}

		return "Item Added to Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartrepo.findByUserId(userId);
		int totalPrice = 0;
		int totalDiscountePrice = 0;
		int totalItem = 0;

		for (CartItem item : cart.getCartItems()) {
			totalPrice = totalPrice + item.getPrice();
			totalDiscountePrice = totalDiscountePrice + item.getDiscountedPrice();
			totalItem = totalItem + item.getQuantity();

		}
		cart.setTotalDiscountedPrice(totalDiscountePrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice - totalDiscountePrice);

		return cartrepo.save(cart);
	}

}
