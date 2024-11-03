package com.manoecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Address;
import com.manoecommerce.entity.Order;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.OrderException;

@Service
public interface OrderService {

	public Order createOrder(User user, Address shippingAddress);

	public Order findOrderById(Long orderId) throws OrderException;

	public List<Order> usersOrderHistory(Long userId) throws OrderException;

	public Order placedOrder(Long orderId) throws OrderException;

	public Order confirmedOrder(Long userId) throws OrderException;

	public Order shippedOrder(Long orderId) throws OrderException;

	public Order deliveredOrder(Long orderId) throws OrderException;

	public Order canceledOrder(Long orderId) throws OrderException;

	public List<Order> getAllOrders();

	public void deleteOrder(Long orderId) throws OrderException;

}
