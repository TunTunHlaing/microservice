package com.e_commerce.order_service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.e_commerce.order_service.model.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "ORDER_DETAILS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	@Column(name = "QUANTITY")
	private Long quantity;
	@Column(name = "ORDER_DATE")
	private LocalDate orderDate;
	@Column(name = "ORDER_STATUS")
	private OrderStatus orderStatus;
	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal amount;
}
