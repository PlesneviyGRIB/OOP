package com.savchenko.shop.main.stat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StatResponse {
    private final String type = "stat";
    @Setter
    private int totalDays;
    @Getter
    private List<CustomerData> customers = new ArrayList<>();
    @Getter @Setter
    private double totalExpenses;
    @Getter @Setter
    private double avgExpenses;

    public void addCustomerData(CustomerData customerData){
        customers.add(customerData);
    }
}
