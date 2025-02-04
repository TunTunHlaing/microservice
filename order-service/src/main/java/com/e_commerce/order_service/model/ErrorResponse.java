package com.e_commerce.order_service.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
}
