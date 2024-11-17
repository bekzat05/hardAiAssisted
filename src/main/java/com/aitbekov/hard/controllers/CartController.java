package com.aitbekov.hard.controllers;

import com.aitbekov.hard.models.CartItem;
import com.aitbekov.hard.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        cartService.addItemToCart(userId, item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/items")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }
}
