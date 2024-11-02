package com.manoecommerce.entity;

import jakarta.persistence.Column;

public class PaymentInfo {
	
	@Column(name = "cardholder_name")
	private String cardHolderName;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "expiration_date")
	private String cardExpirationDate;
	
	@Column(name = "cvv")
	private String cvv;

}
