package com.ankit.ecommerce.orderservice.repository;

import com.ankit.ecommerce.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
