package com.aitbekov.hard.services.impl;

import com.aitbekov.hard.models.Cart;
import com.aitbekov.hard.models.CartItem;
import com.aitbekov.hard.models.Order;
import com.aitbekov.hard.models.OrderItem;
import com.aitbekov.hard.repositories.OrderRepository;
import com.aitbekov.hard.services.CartService;
import com.aitbekov.hard.services.OrderService;
import com.aitbekov.hard.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        List<CartItem> items = cart.getItems();

        double totalAmount = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setTotalAmount(totalAmount);
        order.setItems(items.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            productService.updateStock(item.getProduct().getId(), item.getQuantity());
            return orderItem;
        }).collect(Collectors.toList()));

        cart.getItems().clear();
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
