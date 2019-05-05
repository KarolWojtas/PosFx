package com.karol.tasks;

import com.karol.model.Order;
import com.karol.services.FileService;
import javafx.concurrent.Task;
import java.util.List;

public class ReadOrdersTask extends Task<List<Order>> {
    private FileService fileService = FileService.getInstance();
    @Override
    protected List<Order> call() throws Exception {
        return fileService.readOrdersFromFile("orders.json");
    }
}
