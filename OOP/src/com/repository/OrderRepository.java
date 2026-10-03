package com.repository;

public class OrderRepository {
    private int orderCounter = 1;

    public String generateOrderCode() {
        String code = String.format("ORD-%03d", orderCounter);
        orderCounter++;
        return code;
    }
}