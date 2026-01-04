package com.example.order.domain;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrderNumber(String message) {
        return new OrderNotFoundException("Order with Order NO: " + message + " not found");
    }
}
