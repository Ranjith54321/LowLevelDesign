package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class ShoppingCart {
    private final Map<String, OrderItem> orderItems;

    public ShoppingCart() {
        this.orderItems = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        String productId = product.getId();
        if(orderItems.containsKey(productId)) {
            quantity += orderItems.get(productId).getQuantity();
        }
        orderItems.put(productId, new OrderItem(product, quantity));
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(orderItems.values());
    }

    public void updateItem(String productId, int quantity) {
        if(orderItems.containsKey(productId)) {
            OrderItem item = orderItems.get(productId);
            orderItems.put(productId, new OrderItem(item.getProduct(), quantity));
        }
    }

    public void removeItems(String productId) {
        orderItems.remove(productId);
    }

    public void clearCart() {
        this.orderItems.clear();
    }
}
