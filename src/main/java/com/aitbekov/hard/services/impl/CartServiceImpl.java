package com.aitbekov.hard.services.impl;

import com.aitbekov.hard.models.Cart;
import com.aitbekov.hard.models.CartItem;
import com.aitbekov.hard.repositories.CartItemRepository;
import com.aitbekov.hard.repositories.CartRepository;
import com.aitbekov.hard.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void addItemToCart(Long userId, CartItem item) {
        Cart cart = getCartByUserId(userId);
        item.setCart(cart);
        cartItemRepository.save(item);
    }

    @Override
    public void removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(cartItemRepository::delete);
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = getCartByUserId(userId);
        return cartItemRepository.findByCartId(cart.getId());
    }
}
