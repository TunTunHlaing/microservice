package com.e_commerce.payment_service.service;

import com.e_commerce.common.model.PaymentDetails;
import com.e_commerce.common.model.PaymentRequest;

public interface PaymentService {

	Long makePayment(PaymentRequest request);

	PaymentDetails getPaymentDetailsByOrderId(Long orderId);

}
