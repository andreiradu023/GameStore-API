package com.andreiradu.gamestore.api.repository;

import com.andreiradu.gamestore.api.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemByUserId(Long id);

    CartItem findCartItemByUserIdAndGameId(long customerId, long gameId);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.user.id = ?2 AND c.game.id = ?3")
    void updateGameQuantity(short quantity, long customerId, long gameId);

    void deleteCartItemByUserIdAndGameId(long customerId, long gameId);

    void deleteCartItemByUserId(long customerId);
}
