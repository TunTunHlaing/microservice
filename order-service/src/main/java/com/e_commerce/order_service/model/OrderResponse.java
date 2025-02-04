package com.e_commerce.order_service.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.e_commerce.common.model.PaymentDetails;
import com.e_commerce.common.model.ProductResponse;

import lombok.Builder;

@Builder
public record OrderResponse(
		Long orderId,
		LocalDate orderDate,
		OrderStatus orderStatus,
		BigDecimal amount,
		ProductResponse productResponse,
		PaymentDetails paymentDetails
		) {

}
