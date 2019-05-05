package com.karol.mappers;

public class OrderMapper {
    private static OrderMapper ourInstance = new OrderMapper();

    public static OrderMapper getInstance() {
        return ourInstance;
    }

    private OrderMapper() {
    }
}
