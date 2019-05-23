package com.karol.posfx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.karol.posfx.mappers.OrderMapper;
import com.karol.posfx.model.Order;
import com.karol.posfx.model.dto.OrderDto;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {
    private static FileService ourInstance = new FileService();
    private OrderMapper orderMapper = OrderMapper.getInstance();
    private ObjectMapper objectMapper = orderMapper.getObjectMapper();

    public static FileService getInstance() {
        return ourInstance;
    }

    private FileService() {
    }
    public List<Order> readOrdersFromFile(String fileName){
        List<Order> orders = new ArrayList<>();
        Path filePath = FileSystems.getDefault().getPath(fileName);
        List<OrderDto> orderDtos = null;
        if(Files.exists(filePath)){
            try(InputStream is = Files.newInputStream(filePath)){
                CollectionType orderListType = objectMapper.getTypeFactory().constructCollectionType(List.class, OrderDto.class);
                orderDtos = objectMapper.readValue(is, orderListType);
                Thread.sleep(300);
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(orderDtos != null){
                orders = orderDtos.stream().map(orderMapper::dtoToOrder).collect(Collectors.toList());
            }
        }
        return orders;
    }

    public Order saveOrderToFile(Order order, String fileName){
        Path filePath = FileSystems.getDefault().getPath(fileName);
        List<Order> orders = readOrdersFromFile(fileName);
        orders.add(order);
        if(Files.notExists(filePath)){
            try {
                Files.createFile(filePath);
            } catch(Exception e){
                System.out.println("Create file if !exists: "+e.getMessage());
            }
        }
        try (FileOutputStream os = new FileOutputStream(filePath.toFile(), false)){
            List<OrderDto> dtosToSave = orders.stream().map(orderMapper::orderToDto).collect(Collectors.toList());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(os, dtosToSave);
            return order;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
