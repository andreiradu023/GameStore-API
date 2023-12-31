package com.andreiradu.gamestore.api.repository;

import com.andreiradu.gamestore.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Page<User> findAllByFirstNameContainsOrLastNameContaining(String firstName, String lastName, Pageable pageable);
}
