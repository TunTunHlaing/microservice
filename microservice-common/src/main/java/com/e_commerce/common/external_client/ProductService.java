package com.e_commerce.common.external_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.e_commerce.common.model.ProductResponse;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable(value = "id") Long id);

	@PutMapping("/product/reduce/quantity/{productId}")
	public ResponseEntity<Void> reduceProductQuantity(
			@PathVariable(value = "productId") Long productId, @RequestParam(value = "quantity") Long quantity);
}
