package com.manoecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

	private String paymentMethod;

	private String status;

	private String paymentId;

	private String razorPaymentLinkId;

	private String razorrPaymentLinkReferenceId;

	private String razorPaymentLinkStatus;

	private String razorPaymentId;
}
