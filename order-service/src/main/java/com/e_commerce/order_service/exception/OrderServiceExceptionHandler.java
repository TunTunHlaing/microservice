package com.e_commerce.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.e_commerce.order_service.model.ErrorResponse;

@RestControllerAdvice
public class OrderServiceExceptionHandler {

	
	@ExceptionHandler(exception = OrderServiceCustomException.class)
	public ResponseEntity<ErrorResponse> handleException(OrderServiceCustomException e) {
		
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder()
				.errorCode(e.getErrorCode())
				.errorMessage(e.getMessage())
				.build(),
				HttpStatus.valueOf(e.getErrorStatus())
				);
	}
}
