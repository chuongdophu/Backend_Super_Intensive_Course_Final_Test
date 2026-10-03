package com;

import com.service.InventoryService;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InventoryService service = new InventoryService();
        service.addProduct("iPhone", 1000);
        service.updateStock("iPhone", 10);
        service.addProduct("Samsung", 800);
        service.updateStock("Samsung", 10);

        Map<String, Integer> cart = new HashMap<>();
        cart.put("iPhone", 3);
        cart.put("Samsung", 2);

        service.buy(cart);
    }
}