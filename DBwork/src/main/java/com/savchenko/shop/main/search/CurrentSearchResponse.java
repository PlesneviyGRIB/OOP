package com.savchenko.shop.main.search;

import com.savchenko.shop.models.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class CurrentSearchResponse{
    private Map<?, ?> criteria;
    private List<Customer> results;

    public CurrentSearchResponse(Map<?,?> criteria){
        this.criteria = criteria;
        results = new ArrayList<>();
    }

    public void addCustomer(Customer customer){
        results.add(customer);
    }
}