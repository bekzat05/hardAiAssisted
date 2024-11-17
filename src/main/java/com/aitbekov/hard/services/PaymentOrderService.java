package com.aitbekov.hard.services;

import com.aitbekov.hard.models.Order;
import com.aitbekov.hard.models.User;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentOrderService {

    private final OrderService orderService;
    private final StripeService stripeService;
    private final UserService userService;

    public PaymentOrderService(OrderService orderService, StripeService stripeService, UserService userService) {
        this.orderService = orderService;
        this.stripeService = stripeService;
        this.userService = userService;
    }

    public Order placeOrderWithPayment(Long userId, Double amount, String currency) throws StripeException {
        User user = userService.getUserById(userId);

        // Create a payment intent
        stripeService.createPaymentIntent(amount, currency);

        // Place the order
        return orderService.placeOrder(userId);
    }
}