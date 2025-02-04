package com.e_commerce.common.model;

import java.math.BigDecimal;

public record PaymentRequest(
		Long orderId,
		BigDecimal amount,
		PaymentMethod paymentMethod,
		String referenceNumber)  {

}
