package com.aitbekov.hard.services;

import com.aitbekov.hard.models.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);

    List<Order> getOrdersByUserId(Long userId);
}
