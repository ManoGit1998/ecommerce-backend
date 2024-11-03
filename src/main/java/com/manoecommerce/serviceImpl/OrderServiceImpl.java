package com.manoecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Address;
import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.CartItem;
import com.manoecommerce.entity.OrderItem;
import com.manoecommerce.entity.Orders;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.OrderException;
import com.manoecommerce.repository.AddressRepository;
import com.manoecommerce.repository.CartRepository;
import com.manoecommerce.repository.OrderItemRepository;
import com.manoecommerce.repository.OrdersRepository;
import com.manoecommerce.repository.UserRepository;
import com.manoecommerce.service.CartService;
import com.manoecommerce.service.OrderItemService;
import com.manoecommerce.service.OrderService;
import com.manoecommerce.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CartService cartService;

	@Autowired
	CartRepository cartRepo;

	@Autowired
	ProductService productService;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	OrdersRepository orderRepo;

	@Autowired
	OrderItemRepository orderItemRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	OrderItemService orderItemService;

	@Override
	public Orders createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address = addressRepo.save(shippingAddress);
		user.getAddress().add(address);
		userRepo.save(user);

		Cart cart = cartService.findUserCart(user.getUserId());
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem item : cart.getCartItems()) {

			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());

			orderItems.add(orderItemRepo.save(orderItem));
		}

		Orders createdOrder = new Orders();

		createdOrder.setUser(user);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());

		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());

		Orders savedOrders = orderRepo.save(createdOrder);

		for (OrderItem item : orderItems) {
			item.setOrder(savedOrders);
			orderItemRepo.save(item);
		}

		return savedOrders;
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderException {
		Optional<Orders> order = orderRepo.findById(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		throw new OrderException("No order exist with given order Id - " + orderId);
	}

	@Override
	public List<Orders> usersOrderHistory(Long userId) throws OrderException {
		List<Orders> userOrders = orderRepo.getUserOrders(userId);
		return userOrders;
	}

	@Override
	public Orders placedOrder(Long orderId) throws OrderException {

		Orders order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");

		return orderRepo.save(order);
	}

	@Override
	public Orders confirmedOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepo.save(order);
	}

	@Override
	public Orders shippedOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepo.save(order);
	}

	@Override
	public Orders deliveredOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepo.save(order);
	}

	@Override
	public Orders canceledOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepo.save(order);
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		orderRepo.deleteById(orderId);

	}

}
