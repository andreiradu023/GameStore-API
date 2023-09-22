package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.exception.CartItemException;
import com.andreiradu.gamestore.api.model.CartItem;
import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.model.Game;
import com.andreiradu.gamestore.api.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CartItemService {

    public static final int MAXIMUM_QUANTITY_ALLOWED = 15;

    private final CartItemRepository cartItemRepository;

    private final GameService gameService;

    public List<CartItem> findCartItemByUser(User user) {
        return cartItemRepository.findCartItemByUserId(user.getId());
    }

    public String addGameToCart(User user, long gameId, short quantity) {
        if (quantity > MAXIMUM_QUANTITY_ALLOWED) {
            throw new CartItemException("Could not add " + " item " +
                    " to your shopping cart. Maximum allowed quantity is 15");
        }

        short updatedQuantity = quantity;
        Game game = gameService.getGameById(gameId);
        CartItem cartItem = cartItemRepository.findCartItemByUserIdAndGameId(user.getId(), gameId);

        if (cartItem != null) {
            updatedQuantity = (short) (cartItem.getQuantity() + quantity);
            if (updatedQuantity > MAXIMUM_QUANTITY_ALLOWED) {
                throw new CartItemException("Could not add more " + quantity + " item(s) " +
                        " because there's already " + cartItem.getQuantity() + " item(s) " +
                        " in your shopping cart. Maximum allowed quantity is 15.");
            }
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setGame(game);
        }

        cartItem.setQuantity(updatedQuantity);

        cartItemRepository.save(cartItem);

        return "Game " + game.getTitle() + " x" + updatedQuantity + " has been added to the cart.";
    }

    public String updateGameQuantity(User user, long gameId, short quantity) {
        CartItem cartItem = cartItemRepository.findCartItemByUserIdAndGameId(user.getId(), gameId);
        if (cartItem == null) {
            return "The game is not in the cart.";
        }
        if (quantity > 15) {
            throw new CartItemException("Could not update quantity to " + quantity +
                    ". Maximum allowed is 15.");
        }
        cartItemRepository.updateGameQuantity(quantity, user.getId(), gameId);
        return "Quantity has been updated! New quantity: " + quantity;
    }

    public String removeGameFromCart(User user, long gameId) {
        if (cartItemRepository.findCartItemByUserIdAndGameId(user.getId(), gameId) == null) {
            return "The game is not in the cart";
        }
        cartItemRepository.deleteCartItemByUserIdAndGameId(user.getId(), gameId);
        return "The Game has been removed from your shopping cart.";
    }

    public String deleteCartByUser(User user) {
        if (findCartItemByUser(user).isEmpty()) {
            return "Cart is already empty";
        }
        cartItemRepository.deleteCartItemByUserId(user.getId());
        return "Cart has been deleted!";
    }
}
