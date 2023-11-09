package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.model.CartItem;
import com.andreiradu.gamestore.api.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final OrderService orderService;

    private final CartItemService cartItemService;

    public void placeOrder(User user) {
        List<CartItem> cartItem = cartItemService.findCartItemByUser(user);

        orderService.createOrder(user, cartItem);
        cartItemService.deleteCartByUser(user);
    }
}
