package com.e_commerce.order_service.service;

import com.e_commerce.order_service.model.OrderRequest;
import com.e_commerce.order_service.model.OrderResponse;

public interface OrderService {

	Long createOrder(OrderRequest request);

	OrderResponse getOrderDetails(Long orderId);

}
