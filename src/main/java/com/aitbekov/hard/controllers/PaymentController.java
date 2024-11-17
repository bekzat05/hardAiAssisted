package com.aitbekov.hard.controllers;

import com.aitbekov.hard.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createPayment(@RequestParam Double amount, @RequestParam String currency) {
        try {
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount, currency);
            return ResponseEntity.ok(Map.of("clientSecret", paymentIntent.getClientSecret()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<Map<String, String>> confirmPayment(@RequestParam String paymentIntentId) {
        try {
            PaymentIntent paymentIntent = stripeService.confirmPayment(paymentIntentId);
            return ResponseEntity.ok(Map.of("status", paymentIntent.getStatus()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}