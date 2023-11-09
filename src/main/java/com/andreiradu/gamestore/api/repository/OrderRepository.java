package com.andreiradu.gamestore.api.repository;

import com.andreiradu.gamestore.api.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.firstName LIKE :keyword OR o.user.lastName LIKE :keyword")
    Page<Order> findAllByUser(String keyword, Pageable pageable);
}
