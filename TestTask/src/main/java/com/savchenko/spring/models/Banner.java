package com.savchenko.spring.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "banner")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = -1;
    @Size( min=4, max = 30, message = "\"Name\" length should be in range [4 - 30] characters!")
    private String name;
    @Size( min=1, max = 1024, message = "\"Text\" length should be in range [1 - 1024] characters!")
    private String text;
    @NotEmpty(message = "Choose at least one category!")
    private String categories;
    @DecimalMin(value = "0.0", message = "\"Price\" should be valid and be of type double!")
    private double price;
    private boolean state = true;

    public Banner(){}

    public boolean isDeleted(){ return !state; }

    public void delete(){ state = false; }
}
