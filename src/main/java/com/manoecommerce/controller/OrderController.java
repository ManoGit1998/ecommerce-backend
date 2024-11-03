package com.manoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoecommerce.entity.Address;
import com.manoecommerce.entity.Orders;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.OrderException;
import com.manoecommerce.exception.UserException;
import com.manoecommerce.service.OrderService;
import com.manoecommerce.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<Orders> createOrder(@RequestBody Address shippingAdd,
			@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		Orders order = orderService.createOrder(user, shippingAdd);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public ResponseEntity<List<Orders>> usersOrderHistory(@RequestHeader("Authorization") String jwt)
			throws UserException, OrderException {
		User user = userService.findUserProfileByJwt(jwt);
		List<Orders> orders = orderService.usersOrderHistory(user.getUserId());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Orders> findOrderById(@PathVariable("id") Long id, @RequestHeader("Authorization") String jwt)
			throws UserException, OrderException {
		User user = userService.findUserProfileByJwt(jwt);
		Orders order = orderService.findOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
