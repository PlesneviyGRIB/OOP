package com.savchenko.shop.main.search;

import com.savchenko.shop.models.Customer;
import java.util.ArrayList;
import java.util.List;

class CurrentSearchResponse{
    private Criteria criteria;
    private List<Customer> results;

    public CurrentSearchResponse(Criteria criteria){
        this.criteria = criteria;
        results = new ArrayList<>();
    }

    public void addCustomer(Customer customer){
        results.add(customer);
    }
}