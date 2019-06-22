package com.karol.posfx.model;

import java.util.List;

public class Report {
    private double totalIncome;
    private float totalVolumeOfDrinks;
    private float dishWeightInKilos;
    private List<Order> orders;

    public Report(List<Order> orders){
        this.orders = orders;
        if(orders.size() > 0){
            calculateReport();
        }
    }
    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalVolumeOfDrinks() {
        return totalVolumeOfDrinks;
    }

    public float getDishWeightInKilos() {
        return dishWeightInKilos;
    }

    private void calculateReport(){
        orders.forEach(order -> {
            calcTotalIncome(order);
            calcTotalVolumeOfDrinks(order);
            calcDishWeightInKilos(order);
        });
    }

    private void calcTotalIncome(Order order){
        totalIncome += order.getTotalPrice();
    }

    private void calcTotalVolumeOfDrinks(Order order){
        totalVolumeOfDrinks += order.getProducts().stream()
                .filter(orderItem -> orderItem.getProduct() instanceof Drink)
                .map(orderItem -> ((Drink) orderItem.getProduct()).getVolumeLiters())
                .reduce(0f, Float::sum);
    }

    private void calcDishWeightInKilos(Order order) {
        dishWeightInKilos += order.getProducts().stream()
                .filter(orderItem -> orderItem.getProduct() instanceof Dish )
                .map(orderItem -> ((Dish) orderItem.getProduct()).getWeightGrams() / 1000f)
                .reduce(0f, Float::sum);
    }
}
