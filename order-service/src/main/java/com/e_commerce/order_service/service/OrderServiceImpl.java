package com.e_commerce.order_service.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce.common.external_client.PaymentService;
import com.e_commerce.common.external_client.ProductService;
import com.e_commerce.common.model.PaymentRequest;
import com.e_commerce.order_service.entity.Order;
import com.e_commerce.order_service.exception.OrderServiceCustomException;
import com.e_commerce.order_service.model.OrderRequest;
import com.e_commerce.order_service.model.OrderResponse;
import com.e_commerce.order_service.model.OrderStatus;
import com.e_commerce.order_service.repo.OrderRepo;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	
	private final OrderRepo repo;
	
	private final ProductService productService;
	private final PaymentService paymentService;
	
	@Transactional
	@Override
	public Long createOrder(OrderRequest request) {
		log.info("Order Request Start {}", request);
			
		productService.reduceProductQuantity(request.productId(), request.quantity());
		
		var order  = Order.builder()
				.productId(request.productId())
				.quantity(request.quantity())
				.amount(request.totalAmount())
				.orderStatus(OrderStatus.REQUEST)
				.orderDate(LocalDate.now())
				.build();
		
		var entity = repo.save(order);
		
		log.info("Calling Payment Service for order id {}", order.getId());
		
		var paymentRequest = new PaymentRequest(
				order.getId(), order.getAmount(), request.paymentMethod(), "REF-%s".formatted(order.getId()) );
		OrderStatus orderStatus = null;
		try {
			paymentService.doPayment(paymentRequest);
			orderStatus = OrderStatus.COMPLETED;
		}
		catch (FeignException e) {
		    log.error("FeignException occurred: Status {}, Body {}", e.status(), e.contentUTF8());
		    orderStatus = OrderStatus.FAILED;
		}
		catch (Exception e) {
			e.printStackTrace();
			orderStatus = OrderStatus.FAILED;
			log.error("Error in payment service call!!!");

		}
		
		order.setOrderStatus(orderStatus);
		repo.save(order);
		log.info("Order is created with id {}", entity.getId());
		return entity.getId();
	}

	@Override
	public OrderResponse getOrderDetails(Long orderId) {
		var order = repo.findById(orderId)
				.orElseThrow(() -> new OrderServiceCustomException("Order Not Found for id " + orderId, "ORDER_NOT_FOUND", 404));
		log.info("call to product service for order details {}", orderId);
		var product = productService.getProductById(order.getProductId());
		log.info("product service call complete for order details {}", orderId);
		
		log.info("call to payment service for order details {}", orderId);
		var paymentDetails = paymentService.getPaymentDetailsByOrderId(orderId);
		
		log.info("payment service call complete for order details {}", orderId);

		return  OrderResponse.builder()
				.orderId(order.getId())
				.amount(order.getAmount())
				.orderStatus(order.getOrderStatus())
				.orderDate(order.getOrderDate())
				.productResponse(product.getBody())
				.paymentDetails(paymentDetails.getBody())
				.build();
	}

}
