package com.manoecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Address;
import com.manoecommerce.entity.Orders;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.OrderException;

@Service
public interface OrderService {

	public Orders createOrder(User user, Address shippingAddress);

	public Orders findOrderById(Long orderId) throws OrderException;

	public List<Orders> usersOrderHistory(Long userId) throws OrderException;

	public Orders placedOrder(Long orderId) throws OrderException;

	public Orders confirmedOrder(Long orderId) throws OrderException;

	public Orders shippedOrder(Long orderId) throws OrderException;

	public Orders deliveredOrder(Long orderId) throws OrderException;

	public Orders canceledOrder(Long orderId) throws OrderException;

	public List<Orders> getAllOrders();

	public void deleteOrder(Long orderId) throws OrderException;

}
