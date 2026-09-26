package com.ankit.ecommerce.inventoryservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order-service", path="/orders", url = "${ORDER_SERVICE_URI:}")
public interface OrdersClient {

    @GetMapping("/core/helloOrders")
    String helloOrders();
}