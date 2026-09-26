package com.ankit.ecommerce.inventoryservice.controller;

import com.ankit.ecommerce.inventoryservice.dto.ProductDto;
import com.ankit.ecommerce.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public String fetchOrdersService(HttpServletRequest httpServletRequest){

        log.info(httpServletRequest.getHeader("x-custom-header"));

//        ServiceInstance orderService=discoveryClient.getInstance("order-service");

//        return restClient.get()
//                .uri(orderService.getUri()+/orders/core/helloOrders");
//        .retrieve()
//                .body(String.class);

        return ordersFeignClient.helloOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory(){
        List<ProductDto> inventories=productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
        ProductDto inventory=productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice=productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }
}
