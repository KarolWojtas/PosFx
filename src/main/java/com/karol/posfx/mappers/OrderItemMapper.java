package com.karol.posfx.mappers;

import com.karol.posfx.model.OrderItem;
import com.karol.posfx.model.Product;
import com.karol.posfx.model.dto.EmptyProduct;
import com.karol.posfx.model.dto.OrderItemDto;
import com.karol.posfx.services.ProductService;

import java.util.Optional;

public class OrderItemMapper {
    private static OrderItemMapper ourInstance = new OrderItemMapper();

    public static OrderItemMapper getInstance() {
        return ourInstance;
    }

    private OrderItemMapper() {
    }
    public OrderItemDto orderItemToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProduct().getProductId(), orderItem.getQuantity());
    }
    public OrderItem dtoToOrderItem(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(dto.getQuantity());
        Optional<Product> productOptional = ProductService.getProduct(dto.getProductId());
        if(productOptional.isPresent()){
            orderItem.setProduct(productOptional.get());
        } else {
            orderItem.setProduct(EmptyProduct.getInstance());
        }
        return orderItem;
    }
}
