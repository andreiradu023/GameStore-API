package com.andreiradu.gamestore.api.repository;

import com.andreiradu.gamestore.api.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Page<Game> findAllByTitleContaining(String title, Pageable pageable);
}
