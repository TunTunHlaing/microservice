package com.e_commerce.order_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_commerce.order_service.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
