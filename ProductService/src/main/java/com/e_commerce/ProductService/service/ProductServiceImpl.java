package com.e_commerce.ProductService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.ProductService.entity.Product;
import com.e_commerce.ProductService.exception.ProductServiceException;
import com.e_commerce.ProductService.repo.ProductRepo;
import com.e_commerce.common.model.ProductRequest;
import com.e_commerce.common.model.ProductResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo repo;

    @Override
    public long addProduct(ProductRequest request) {
        log.info("Adding product....");
        var product = repo.save(
        		Product.builder()
        		.productName(request.name())
        		.quantity(request.quantity())
        		.price(request.price())
        		.build()
        		);
        log.info("Product created.");
        return product.getId();
    }

	@Override
	public List<ProductResponse> getProducts() {
		return repo.findAll().stream().map(this::toResponse).toList();
	}

	@Override
	public ProductResponse getProductById(Long id) {
		log.info("GetProductById Api start with id {} ", id);
		var product = repo.findById(id)
				.orElseThrow(() -> new ProductServiceException("Product with given id not found!!!", "PRODUCT_NOT_FOUND"));
		log.info("GetProductById Api Success");

		return toResponse(product);
	}


	@Override
	public void reduceProductQuantity(Long productId, Long quantity) {
		log.info("Product Quantity Reduce Start With Product Id {} and quantity {}",productId, quantity);
		var product = repo.findById(productId)
				.orElseThrow(() -> new ProductServiceException("Product with given id not found!!!", "PRODUCT_NOT_FOUND"));
		
		if (product.getQuantity() < quantity) {
			throw new ProductServiceException("Product have not enough quantity!!!", "PRODUCT_QUANTITY_NOT_ENOUGH");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		
		repo.saveAndFlush(product);
		
		log.info("Product Quantity Reduce successfully for product id {}", product.getId());
		
	}
	
	private ProductResponse toResponse( Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.productName(product.getProductName())
				.quantity(product.getQuantity())
				.price(product.getPrice())
				.build();
	}
}
