package com.andreiradu.gamestore.api.controller;

import com.andreiradu.gamestore.api.model.CartItem;
import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.service.CartItemService;
import com.andreiradu.gamestore.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    private final UserService userService;

    @GetMapping()
    public List<CartItem> viewCart(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return cartItemService.findCartItemByUser(user);
    }

    @PostMapping("/add/{gameId}/{quantity}")
    public void addGameToCart(@PathVariable long gameId, @PathVariable short quantity,
                              Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        cartItemService.addGameToCart(user, gameId, quantity);
    }

    @PutMapping("/update/{gameId}/{quantity}/{email}")
    public String updateGameQuantity(@PathVariable long gameId, @PathVariable short quantity,
                                     Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return cartItemService.updateGameQuantity(user, gameId, quantity);
    }

    @DeleteMapping("/remove/{gameId}/{email}")
    public String removeGameFromCart(@PathVariable long gameId, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return cartItemService.removeGameFromCart(user, gameId);
    }

    @DeleteMapping()
    public String deleteCart(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return cartItemService.deleteCartByUser(user);
    }
}
