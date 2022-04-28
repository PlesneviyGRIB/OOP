package com.savchenko.shop.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Customer {
    private int id;
    private String name;
    private String lastName;
}
