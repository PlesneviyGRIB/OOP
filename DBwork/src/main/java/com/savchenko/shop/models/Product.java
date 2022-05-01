package com.savchenko.shop.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Product {
    private transient int id;
    private String name;
    private double expenses;
}

