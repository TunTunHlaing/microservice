package com.e_commerce.order_service.model;

import java.math.BigDecimal;

import com.e_commerce.common.model.PaymentMethod;

import lombok.Builder;

@Builder
public record OrderRequest(
		Long productId,
		BigDecimal totalAmount,
		Long quantity,
		PaymentMethod paymentMethod
		) {


}
