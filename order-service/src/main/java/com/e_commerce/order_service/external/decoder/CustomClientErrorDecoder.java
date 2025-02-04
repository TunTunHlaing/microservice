package com.e_commerce.order_service.external.decoder;

import com.e_commerce.order_service.exception.OrderServiceCustomException;
import com.e_commerce.order_service.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomClientErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {

		var objectMapper = new ObjectMapper();
		
		log.error("Error Request Url {}", response.request().url());
		log.error("Error Request Header {}", response.request().headers());
		
		log.error("Error Body {}", response.body());

		try {
			var errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new OrderServiceCustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status());
		} catch (Exception e) {
			e.printStackTrace();
			return new OrderServiceCustomException("Internal Server Error", "SERVER_ERROR", 500);
		}
	}

}
