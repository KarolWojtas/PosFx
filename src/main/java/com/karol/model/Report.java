package com.karol.model;

import java.util.List;

public class Report {
    private double totalIncome;
    private double totalVolumeOfDrinks;
    private List<Order> orders;

    public Report(List<Order> orders){
        this.orders = orders;
    }
    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalVolumeOfDrinks() {
        return totalVolumeOfDrinks;
    }

    public void setTotalVolumeOfDrinks(double totalVolumeOfDrinks) {
        this.totalVolumeOfDrinks = totalVolumeOfDrinks;
    }
    public void addToTotalPrice(double price){
        totalIncome += price;
    }
    public void addToTotalVolumeOfDrinks(double volumeInLiters){
        totalVolumeOfDrinks += volumeInLiters;
    }
}
