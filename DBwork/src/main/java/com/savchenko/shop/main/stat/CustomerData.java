package com.savchenko.shop.main.stat;

import com.savchenko.shop.models.Product;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class CustomerData {
    private String name;
    private List<Product> purchases;
    private double totalExpenses;
}
