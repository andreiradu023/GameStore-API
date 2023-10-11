package com.andreiradu.gamestore.api.controller;

import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.service.CheckoutService;
import com.andreiradu.gamestore.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    private final UserService userService;

    @PostMapping("{email}")
    private String placeOrder(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        return checkoutService.placeOrder(user);
    }
}
