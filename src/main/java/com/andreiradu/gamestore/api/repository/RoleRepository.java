package com.andreiradu.gamestore.api.repository;

import com.andreiradu.gamestore.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
