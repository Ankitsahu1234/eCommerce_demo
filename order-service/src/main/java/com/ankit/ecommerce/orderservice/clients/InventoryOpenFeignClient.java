package com.ankit.ecommerce.orderservice.clients;

import com.ankit.ecommerce.orderservice.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="inventory-service", path="/inventory", url = "${INVENTORY_SERVICE_URI:}")
public interface InventoryOpenFeignClient {

    @PutMapping("/products/reduce-stocks")
    Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto);
}