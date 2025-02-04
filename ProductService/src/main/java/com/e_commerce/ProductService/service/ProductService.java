package com.e_commerce.ProductService.service;

import java.util.List;

import com.e_commerce.common.model.ProductRequest;
import com.e_commerce.common.model.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest request);
    
    List<ProductResponse> getProducts ();
    

	ProductResponse getProductById(Long id);

	void reduceProductQuantity(Long productId, Long quantity);
}
