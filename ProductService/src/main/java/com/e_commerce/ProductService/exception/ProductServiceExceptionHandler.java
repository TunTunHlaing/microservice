package com.e_commerce.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.e_commerce.ProductService.model.ErrorResponse;

@RestControllerAdvice
public class ProductServiceExceptionHandler {
	
	@ExceptionHandler(exception = ProductServiceException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException (ProductServiceException e) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder()
				.errorCode(e.getErrorCode())
				.errorMessage(e.getMessage())
				.build(),
				HttpStatus.NOT_FOUND
				);
	}

}
