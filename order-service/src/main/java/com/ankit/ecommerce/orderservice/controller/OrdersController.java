package com.ankit.ecommerce.orderservice.controller;

import com.ankit.ecommerce.orderservice.dto.OrderRequestDto;
import com.ankit.ecommerce.orderservice.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService ordersService;

    @Value("${my.variable}")
    private String myVariable;

    private final FeaturesEnableConfig featuresEnableConfig;

    @GetMapping("/helloOrders")
    public String helloOrders({
        if(featuresEnableConfig.isUserTrackingEnabled()){
            return "User tracking enabled wohoo, my variable is: :"+myVariable+" ";
        }else{
            return "User tracking disabled awww, my variable is: :"+myVariable+" ";
        }

    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto orderRequestDto1=ordersService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);

    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest){
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders=ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
        log.info("Fetching orders with ID: {} via controller", id);
        OrderRequestDto order=ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
