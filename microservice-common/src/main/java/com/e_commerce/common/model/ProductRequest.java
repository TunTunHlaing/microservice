package com.e_commerce.common.model;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price,
        Long quantity
) {

   
}
