package com.savchenko.spring.models;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Category {
    private static int cnt = 0;

    private int id = cnt++;
    private boolean state = true;
    private String name;
    private String requestId;

    public Category(String name, String requestId) {
        this.name = name;
        this.requestId = requestId;
    }

    public boolean isDeleted(){ return !state; }

    public void delete(){ state = false; }
}
