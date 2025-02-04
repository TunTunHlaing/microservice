package com.e_commerce.common.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record PaymentDetails(
		Long transactionId,
		Long orderId,
		BigDecimal amount,
		PaymentMethod paymentMethod,
		String referenceNumber,
		LocalDateTime paymentDateTime
) {

}
