package org.example;

import java.util.List;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class Order {
    private final String id;
    private final User user;
    private final List<OrderItem> items;
    private final double totalAmount;
    private OrderStatus orderStatus;

    public Order(String id, User user, List<OrderItem> items) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalAmount = calculateAmount(items);
        this.orderStatus = OrderStatus.PENDING;
    }

    private double calculateAmount(List<OrderItem> items) {
        double total=0;
        for(OrderItem item: items) {
            total+=item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double calculateAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
