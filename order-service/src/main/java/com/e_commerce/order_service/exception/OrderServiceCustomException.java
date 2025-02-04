package com.e_commerce.order_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderServiceCustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private int errorStatus;
	
	public OrderServiceCustomException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.errorStatus = status;
	}
}
