package com.manoecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.OrderItem;
import com.manoecommerce.repository.OrderItemRepository;
import com.manoecommerce.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepo;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepo.save(orderItem);
	}

}
