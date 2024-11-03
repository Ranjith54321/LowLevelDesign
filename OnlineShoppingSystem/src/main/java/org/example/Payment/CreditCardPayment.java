package org.example.Payment;

/**
 * Created by Ranjith S on 03/11/24.
 **/
public class CreditCardPayment implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}
