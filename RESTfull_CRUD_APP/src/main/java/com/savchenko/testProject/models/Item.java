package com.savchenko.testProject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "item")
@Getter @Setter
@NoArgsConstructor
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message="Field couldn't be empty")
    private String type;
    @NotEmpty(message="Field couldn't be empty")
    private String country;
    @NotEmpty(message="Field couldn't be empty")
    private String manufacture;
    private Boolean onlineOrder;
    private Boolean credit;


    public Item(String type, String country, String manufacture, Boolean onlineOrder, Boolean credit) {
        this.type = type;
        this.country = country;
        this.manufacture = manufacture;
        this.onlineOrder = onlineOrder;
        this.credit = credit;
    }

    @Override
    public String toString(){
        return type.toString() + " " + manufacture;
    }
}