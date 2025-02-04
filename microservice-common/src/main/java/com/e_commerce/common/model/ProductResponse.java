package com.e_commerce.common.model;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ProductResponse(
        Long id,
        String productName,
        BigDecimal price,
        Long quantity
) {
	

}
