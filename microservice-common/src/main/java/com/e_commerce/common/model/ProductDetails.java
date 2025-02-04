package com.e_commerce.common.model;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ProductDetails(
		Long productId,
		BigDecimal price,
		Long quantity) {

}
