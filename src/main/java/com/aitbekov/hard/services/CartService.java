package com.aitbekov.hard.services;

import com.aitbekov.hard.models.Cart;
import com.aitbekov.hard.models.CartItem;

import java.util.List;

public interface CartService {
    Cart getCartByUserId(Long userId);

    void addItemToCart(Long userId, CartItem item);

    void removeItemFromCart(Long userId, Long productId);

    List<CartItem> getCartItems(Long userId);
}