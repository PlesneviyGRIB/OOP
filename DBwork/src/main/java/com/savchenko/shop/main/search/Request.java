package com.savchenko.shop.main.search;

import com.savchenko.shop.DAO.CustomerDAO;
import com.savchenko.shop.DAO.ProductDAO;
import com.savchenko.shop.DAO.PurchaseDAO;
import com.savchenko.shop.models.Customer;
import com.savchenko.shop.models.Product;
import com.savchenko.shop.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Request {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private PurchaseDAO purchaseDAO;

    public List<Customer> lastName(String lastname){
        return customerDAO.getByLastname(lastname);
    }

    public List<Customer> titleAndCount(String title, double cnt){
        List<Product> products = productDAO.getByTitle(title);
        List<Customer> customers = new ArrayList<>();

        for(Product product: products){
            Map<Integer,Integer> entries = new HashMap<>();
            for(Purchase purchase: purchaseDAO.getByProductId(product.getId())){
                if(entries.containsKey(purchase.getCustomerId()))
                    entries.put(purchase.getCustomerId(), entries.get(purchase.getCustomerId()) + 1);
                else entries.put(purchase.getCustomerId(), 1);
            }
            entries.keySet().forEach(key -> { if(entries.get(key) >= cnt) customers.add(customerDAO.getById(key)); });
        }
        return customers;
    }

    public List<Customer> minMaxCost(double min, double max){
        Map<Integer, Double> map = new HashMap<>();

        for (Customer customer: customerDAO.getAll()){
            map.put(customer.getId(), 0.0);
            for(Purchase purchase: purchaseDAO.getByCustomerId(customer.getId())){
                map.put(customer.getId(), map.get(customer.getId()) + productDAO.getById(purchase.getProductId()).getCost());
            }
        }
        List<Customer> customers = new ArrayList<>();
        map.keySet().forEach(key -> { if(min <= map.get(key) && map.get(key) <= max) customers.add(customerDAO.getById(key)); });

        return customers;
    }

    public List<Customer> passiveCustomers(double cnt){
        Map<Integer, Integer> map = new HashMap<>();
        customerDAO.getAll().forEach(customer -> map.put(customer.getId(), purchaseDAO.getByCustomerId(customer.getId()).size()));
        List<Map.Entry<Integer, Integer>> entries = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();

        List<Customer> customers = new ArrayList<>();

        for(int i = 0; i< cnt; i++) customers.add(customerDAO.getById(entries.get(i).getKey()));

        return customers;
    }
}
