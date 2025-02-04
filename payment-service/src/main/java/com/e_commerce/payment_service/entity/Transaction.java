package com.e_commerce.payment_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.e_commerce.common.model.PaymentMethod;

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
@Table(name = "TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "ORDER_ID", nullable = false)
	private Long orderId;
	@Column(name = "PAYMENT_METHOD", nullable = false)
	private PaymentMethod paymentMethod;
	@Column(name = "REFERENCE_NUMBER", nullable = false)
	private String referenceNumber;
	@Column(name = "PAYMENT_DATE_TIME", nullable = false)
	private LocalDateTime paymentDateTime;
	@Column(name = "PAYMENT_STATUS", nullable = false)
	private String paymentStatus;
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
}
