package com.savchenko.shop.main.supportive;

import lombok.Getter;

@Getter
public class OperationError {
    private final String type = "error";
    private String message;

    public OperationError(String message) {
        this.message = message;
    }
}
