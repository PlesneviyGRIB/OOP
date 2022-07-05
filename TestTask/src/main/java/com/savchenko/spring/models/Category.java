package com.savchenko.spring.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
@ToString
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = -1;
    private boolean state = true;
    @Size( min=4, max = 30, message = "\"Name\" length should be in range [4 - 30] characters!")
    private String name;
    @Size( min=1, max = 16, message = "\"RequestID\" length should in range [1 - 16] characters!")
    private String requestId;

    public Category(String name, String requestId) {
        this.name = name;
        this.requestId = requestId;
    }

    public Category(){}

    public boolean isDeleted(){ return !state; }

    public void delete(){ state = false; }
}
