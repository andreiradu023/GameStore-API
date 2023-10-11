package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.exception.ResourceNotFoundException;
import com.andreiradu.gamestore.api.model.CartItem;
import com.andreiradu.gamestore.api.model.Game;
import com.andreiradu.gamestore.api.model.Order;
import com.andreiradu.gamestore.api.model.OrderItem;
import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order not found with id: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setUser(updatedOrder.getUser());
        existingOrder.setOrderDate(updatedOrder.getOrderDate());
        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long orderId) {
        getOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    public Order createOrder(User user, List<CartItem> cartItems) {
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setTotalPrice(calculateTotal(cartItems));
        newOrder.setOrderDate(new Date());
        Set<OrderItem> orderItems = newOrder.getOrderItems();

        for (CartItem cartItem : cartItems) {
            Game game = cartItem.getGame();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(newOrder);
            orderItem.setGameId(game);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(game.getPrice());
            orderItem.setSubtotal(cartItem.getQuantity() * game.getPrice().floatValue());

            orderItems.add(orderItem);
        }
        newOrder.setOrderItems(orderItems);

        return orderRepository.save(newOrder);
    }

    private float calculateTotal(List<CartItem> cartItems) {
        float total = 0.0F;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubtotal();
        }
        return total;
    }
}
