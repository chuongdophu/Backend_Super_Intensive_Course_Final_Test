package com;

import com.service.InventoryService;

public class Main {
    public static void main(String[] args) {
        InventoryService service = new InventoryService();
        service.addProduct("iPhone", 1000);
        service.updateStock("iPhone", -5);
    }
}