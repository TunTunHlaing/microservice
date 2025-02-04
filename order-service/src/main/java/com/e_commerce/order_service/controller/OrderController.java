package com.e_commerce.order_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.order_service.model.OrderRequest;
import com.e_commerce.order_service.model.OrderResponse;
import com.e_commerce.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

	private final OrderService service;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Long> createOrder(@RequestBody OrderRequest request) {
		var orderId = service.createOrder(request);
		log.info("Order Id = {}", orderId);
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderId) {
		var orderResponse = service.getOrderDetails(orderId);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
}
