package org.example;

import org.example.Payment.PaymentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class OnlineShoppingService {
    private static OnlineShoppingService instance;
    private final Map<String, User> userMap;
    private final Map<String, Product> productMap;
    private final Map<String, Order> orderMap;

    private OnlineShoppingService() {
        this.userMap = new HashMap<>();
        this.productMap = new HashMap<>();
        this.orderMap = new HashMap<>();
    }

    public static synchronized OnlineShoppingService getInstance() {
        if(instance == null) {
            instance = new OnlineShoppingService();
        }
        return instance;
    }

    public synchronized Order placeOrder(User user, ShoppingCart cart, PaymentService paymentService) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem item: cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            if(product.isProductAvailable(quantity)) {
                product.updateQuantity(-quantity); // adjust quantity
                orderItems.add(item);
            }
        }

        if(orderItems.isEmpty()) {
            throw new IllegalArgumentException("products not available");
        }

        String orderId = generateOrderId();
        Order order = new Order(orderId, user, orderItems);
        orderMap.put(orderId, order);
        user.addOrders(order);
        cart.clearCart();

        if(paymentService.processPayment(order.getTotalAmount())) {
            order.setOrderStatus(OrderStatus.PROCESSING);
        } else {
            order.setOrderStatus(OrderStatus.PROCESSING);
            // revert the quantity
            for(OrderItem item: orderItems) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                product.updateQuantity(quantity);
            }
        }
        return order;
    }

    public List<Product> searchProducts(String keyWord) {
        List<Product> matchedProducts = new ArrayList<>();
        for(Product product: productMap.values()) {
            if(product.getName().toLowerCase().contains(keyWord)) {
                matchedProducts.add(product);
            }
        }
        return matchedProducts;
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public Product getProduct(String productId) {
        return productMap.get(productId);
    }

    public Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }

    public void registerUser(User user) {
        userMap.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public void addOrder(Order order) {
        orderMap.put(order.getId(), order);
    }

    public String generateOrderId() {
        return "ORDER" + UUID.randomUUID();
    }
}
