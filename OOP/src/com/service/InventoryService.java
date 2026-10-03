package com.service;

import com.model.Product;
import com.repository.ProductRepository;

public class InventoryService {
    private ProductRepository productRepository = new ProductRepository();

    public void addProduct(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Product name must not be empty.");
            return;
        }
        if (price <= 0) {
            System.out.println("Price must be greater than 0.");
            return;
        }
        if (productRepository.exists(name)) {
            System.out.println("Product already exists.");
            return;
        }
        Product product = new Product(name, price, 0);
        productRepository.save(product);
        System.out.println("Product\nName: " + product.getName() + "\nPrice: " + (int) product.getPrice() + "\nStock: "
                + product.getStock());
    }

    public void updateStock(String productName, int stockChange) {
        Product product = productRepository.findByName(productName);
        if (product == null) {
            System.out.println("Product must exist.");
            return;
        }
        int newStock = product.getStock() + stockChange;
        if (newStock < 0) {
            newStock = 0;
        }
        product.setStock(newStock);
        System.out.println(product.getName() + " stock = " + product.getStock());
    }
}