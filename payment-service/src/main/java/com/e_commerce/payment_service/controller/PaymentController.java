package com.e_commerce.payment_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.common.model.PaymentDetails;
import com.e_commerce.common.model.PaymentRequest;
import com.e_commerce.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService service;
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request) {
		return new ResponseEntity<Long>(service.makePayment(request), HttpStatus.OK);
	}
	
	@GetMapping("{orderId}")
	public ResponseEntity<PaymentDetails> getPaymentDetailsByOrderId(@PathVariable Long orderId) {
		
		var paymentDetails = service.getPaymentDetailsByOrderId(orderId);
		return new ResponseEntity<PaymentDetails>(paymentDetails, HttpStatus.OK);

	}
	
}
