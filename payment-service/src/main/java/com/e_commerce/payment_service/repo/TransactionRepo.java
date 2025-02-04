package com.e_commerce.payment_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_commerce.payment_service.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long>{
	
	
	Optional<Transaction> findByOrderId(Long orderId);
	

}
