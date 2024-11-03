package com.manoecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "order_id")
	private String orderid;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	private LocalDateTime oderDate;

	private LocalDateTime deliveryDate;

	@OneToOne
	private Address shippingAddress;

	@Embedded
	private PaymentDetails paymentDetails = new PaymentDetails();

	private double totalPrice;

	private Integer totalDiscountedPrice;

	private Integer discount;
	
	private int totalItem;

	private String orderStatus;

	private LocalDateTime orderDate;
	
	private LocalDateTime createdAt;

}
