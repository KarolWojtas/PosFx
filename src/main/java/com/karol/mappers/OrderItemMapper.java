package com.karol.mappers;

public class OrderItemMapper {
    private static OrderItemMapper ourInstance = new OrderItemMapper();

    public static OrderItemMapper getInstance() {
        return ourInstance;
    }

    private OrderItemMapper() {
    }
}
