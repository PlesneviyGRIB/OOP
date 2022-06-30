package com.savchenko.spring.models;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Banner {
    private static int cnt=0;
    private int id = cnt++;
    private boolean state;
    private String name;
    private String text;
    private double price;
    private List<Category> categories;

    public Banner(String name, String text, double price, List<Category> categories) {
        this.name = name;
        this.text = text;
        this.price = price;
        this.categories = categories;
    }

    public boolean isDeleted(){ return !state; }

    public void delete(){ state = false; }
}
