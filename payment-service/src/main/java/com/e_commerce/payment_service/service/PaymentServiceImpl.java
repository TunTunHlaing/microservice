package com.e_commerce.payment_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.e_commerce.common.model.PaymentDetails;
import com.e_commerce.common.model.PaymentRequest;
import com.e_commerce.payment_service.entity.Transaction;
import com.e_commerce.payment_service.repo.TransactionRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{
	
	private final TransactionRepo repo;

	@Override
	public Long makePayment(PaymentRequest request) {
		log.info("Payment request start {}", request);
		var transaction = Transaction.builder()
				.amount(request.amount())
				.orderId(request.orderId())
				.paymentMethod(request.paymentMethod())
				.paymentDateTime(LocalDateTime.now())
				.referenceNumber(request.referenceNumber())
				.paymentStatus("SUCCESS")
				.build();
		
		var entity = repo.save(transaction);
		log.info("Transaction with id {} save successfully.", entity.getId());
				
		return entity.getId();
	}

	@Override
	public PaymentDetails getPaymentDetailsByOrderId(Long orderId) {
		
		var entity = repo.findByOrderId(orderId).orElseThrow(
				() -> new RuntimeException("Payment Details not found for order id %s".formatted(orderId)));
		
		return PaymentDetails.builder()
				.transactionId(entity.getId())
				.orderId(entity.getOrderId())
				.amount(entity.getAmount())
				.referenceNumber(entity.getReferenceNumber())
				.paymentMethod(entity.getPaymentMethod())
				.paymentDateTime(entity.getPaymentDateTime())
				.build();
	}

}
