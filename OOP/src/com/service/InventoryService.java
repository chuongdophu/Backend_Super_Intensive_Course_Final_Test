package com.service;

import com.model.Product;
import com.model.OrderItem;
import com.repository.ProductRepository;
import com.repository.OrderRepository;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class InventoryService {
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();

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

    public void buy(Map<String, Integer> cart) {
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            Product product = productRepository.findByName(entry.getKey());
            if (product == null) {
                System.out.println("Product not found: " + entry.getKey());
                return;
            }
            if (product.getStock() < entry.getValue()) {
                System.out.println("Insufficient stock for: " + entry.getKey());
                return;
            }
        }

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            Product product = productRepository.findByName(entry.getKey());
            int qty = entry.getValue();
            product.setStock(product.getStock() - qty);
            items.add(new OrderItem(product.getName(), product.getPrice(), qty));
            total += product.getPrice() * qty;
        }

        String orderCode = orderRepository.generateOrderCode();

        System.out.println("Order Code: " + orderCode);
        System.out.println("Items:");
        for (OrderItem item : items) {
            System.out.println(item.getProductName() + " x " + item.getQuantity() + " $" + (int) item.getPrice());
        }
        System.out.println("\nTotal: $" + (int) total);
        System.out.println("\nInventory:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            Product product = productRepository.findByName(entry.getKey());
            System.out.println(product.getName() + " stock = " + product.getStock());
        }
    }
}