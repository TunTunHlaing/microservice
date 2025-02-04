package com.e_commerce.common.external_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.e_commerce.common.model.PaymentDetails;
import com.e_commerce.common.model.PaymentRequest;


@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentService {

	@PostMapping("/payment/")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);
	
	@GetMapping("/payment/{orderId}")
	public ResponseEntity<PaymentDetails> getPaymentDetailsByOrderId(@PathVariable(value = "orderId") Long orderId);
	
	
}
