package com.manoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoecommerce.entity.Orders;
import com.manoecommerce.exception.OrderException;
import com.manoecommerce.response.ApiResponse;
import com.manoecommerce.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")

public class AdminOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/")
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> allOrders = orderService.getAllOrders();
		return new ResponseEntity<List<Orders>>(allOrders, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Orders> confirmOrders(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)
			throws OrderException {
		Orders confirmedOrder = orderService.confirmedOrder(orderId);
		return new ResponseEntity<>(confirmedOrder, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Orders> shipOrders(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)
			throws OrderException {
		Orders confirmedOrder = orderService.shippedOrder(orderId);
		return new ResponseEntity<>(confirmedOrder, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Orders> deliverOrders(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)
			throws OrderException {
		Orders confirmedOrder = orderService.deliveredOrder(orderId);
		return new ResponseEntity<>(confirmedOrder, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Orders> cancelOrders(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)
			throws OrderException {
		Orders confirmedOrder = orderService.canceledOrder(orderId);
		return new ResponseEntity<>(confirmedOrder, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> deleteOrders(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException {
		orderService.deleteOrder(orderId);
		ApiResponse res = new ApiResponse();
		res.setMessage("Order Deleted Successfully");
		res.setStatus(true);

		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

}
