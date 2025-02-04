package com.e_commerce.ProductService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.ProductService.service.ProductService;
import com.e_commerce.common.model.ProductRequest;
import com.e_commerce.common.model.ProductResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest request) {
		long productId = service.addProduct(request);
		return new ResponseEntity<>(productId, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getProducts() {
		return new ResponseEntity<>(service.getProducts(), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);

	}

	@PutMapping("reduce/quantity/{productId}")
	public ResponseEntity<Void> reduceProductQuantity(HttpServletRequest servletRequest,
			@PathVariable(value = "productId") Long productId, @RequestParam(value = "quantity") Long quantity) {
		
		log.info("Incoming reduce quantity url {}", servletRequest.getRequestURI());
		service.reduceProductQuantity(productId, quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
