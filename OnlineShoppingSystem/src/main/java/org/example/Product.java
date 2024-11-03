package org.example;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class Product {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private int quantity; // it is not final, since we update the quantity when order is placed.

    public Product(String id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public synchronized void updateQuantity(int quantity) {
            this.quantity += quantity;
    }

    public synchronized boolean isProductAvailable(int requiredQuantity) {
        return quantity >= requiredQuantity;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
