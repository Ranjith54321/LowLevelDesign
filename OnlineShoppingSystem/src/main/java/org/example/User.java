package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class User {
    private final String id;
    private final String userName;
    private final String email;
    private final String password;
    private final List<Order> orders;

    public User(String id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    private String getPassword() {
        return password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrders(Order order) {
        orders.add(order);
    }
}
