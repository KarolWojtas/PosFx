package com.karol.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karol.model.Order;
import com.karol.model.OrderItem;
import com.karol.model.dto.OrderDto;
import com.karol.model.dto.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private static OrderMapper ourInstance = new OrderMapper();
    private OrderItemMapper orderItemMapper = OrderItemMapper.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    public static OrderMapper getInstance() {
        return ourInstance;
    }

    private OrderMapper() {
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public OrderDto orderToDto(Order order){
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderer(order.getOrderer());
        dto.setCreated(order.getCreated());
        List<OrderItemDto> orderItemDtos = order.getProducts().stream().map(orderItemMapper::orderItemToDto).collect(Collectors.toList());
        dto.setOrderItems(orderItemDtos);
        return dto;
    }

    public Order dtoToOrder(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCreated(dto.getCreated());
        order.setOrderer(dto.getOrderer());
        order.setTotalPrice(dto.getTotalPrice());
        List<OrderItem> orderItems = dto.getOrderItems().stream().map(orderItemMapper::dtoToOrderItem).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        return order;
    }
}
