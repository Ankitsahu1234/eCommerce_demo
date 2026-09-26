package com.ankit.ecommerce.inventoryservice.service;

import com.ankit.ecommerce.inventoryservice.dto.ProductDto;
import com.ankit.ecommerce.inventoryservice.entity.Product;
import com.ankit.ecommerce.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory(){
        log.info("Fetching all inventory items");
        List<Product> inventories=productRepository.findAll();
        return inventories.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching Product with ID: {}", id);

        Product inventory = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        return modelMapper.map(inventory, ProductDto.class);
    }

    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto){
        log.info("Reducing the stocks");
        Double totalPrice=0;
        for(OrderRequestItemDto orderRequestItemDto: orderRequestDto.getItems()){
            final Long productId=orderRequestDto.getProductId();
            Integer quantity=orderRequestDto.getQuantity();

            Product product=productRepository.findById(productId).orElseThrow(()->
                    new RuntimeException("Product not found with id: "+productId));
            if(product.getStock() < quantity){
                throw new RuntimeException("Product cannot be fulfilled for given quantity");
            }

            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            totalPrice+=quantity*product.getPrice();
        }
        return totalPrice;
    }
}
