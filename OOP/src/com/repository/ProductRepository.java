package com.repository;

import com.model.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<String, Product> productMap = new HashMap<>();

    public Product findByName(String name) {
        return productMap.get(name);
    }

    public void save(Product product) {
        productMap.put(product.getName(), product);
    }

    public boolean exists(String name) {
        return productMap.containsKey(name);
    }
}