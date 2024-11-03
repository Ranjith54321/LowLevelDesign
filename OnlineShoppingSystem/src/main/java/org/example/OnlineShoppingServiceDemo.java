package org.example;

import org.example.Payment.CreditCardPayment;
import org.example.Payment.PaymentService;

import java.util.List;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class OnlineShoppingServiceDemo {
    public static void main(String[] args) {
        System.out.println("OnlineShopping Service.....");

        OnlineShoppingService shoppingService = OnlineShoppingService.getInstance();
        PaymentService creditCardPayment = new CreditCardPayment();

        // create users
        User user1 = new User("U001", "user1", "user1@gmail.com", "user1");
        User user2 = new User("U002", "user2", "user2@gmail.com", "user2");
        shoppingService.registerUser(user1);
        shoppingService.registerUser(user2);

        // create products
        Product product1 = new Product("P001", "SmartPhone", "Apple i-phone", 1000, 10);
        Product product2 = new Product("P002", "Laptop", "Apple mac book", 2000, 4);
        shoppingService.addProduct(product1);
        shoppingService.addProduct(product2);

        // user-1 purchase
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addItem(product1, 2);
        cart1.addItem(product1, 1);
        cart1.addItem(product2, 1);
        Order order = shoppingService.placeOrder(user1, cart1, creditCardPayment);
        System.out.println("Order ID: " + order.getId());

        System.out.println(" ---- ----- ");
        // user-2 search product and purchase:
        List<Product> searchResult = shoppingService.searchProducts("laptop");
        if(!searchResult.isEmpty()) {
            ShoppingCart cart2 = new ShoppingCart();
            cart2.addItem(searchResult.get(0), 2);
            Order order2 = shoppingService.placeOrder(user2, cart2, creditCardPayment);
            System.out.println("Order ID: " + order2.getId() + "Items : " + order2.getItems() + " \n TotalAmount : " + order2.getTotalAmount());
        }
        System.out.println(" ---- ----- ");
        // user-1 order history:
        for(Order order1: user1.getOrders()) {
            System.out.println("Order ID: " + order1.getId() + " \n Items : " + order1.getItems() + " \n TotalAmount : " + order1.getTotalAmount());
            System.out.println(" OrderStatus : " + order1.getOrderStatus());
        }
    }
}
