package com.manoecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Address;
import com.manoecommerce.entity.Order;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.OrderException;
import com.manoecommerce.repository.CartRepository;
import com.manoecommerce.service.CartService;
import com.manoecommerce.service.OrderService;
import com.manoecommerce.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CartService cartItemService;

	@Autowired
	CartRepository cartRepo;

	@Autowired
	ProductService productService;

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(Long userId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub

	}

}
