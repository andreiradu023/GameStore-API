package com.andreiradu.gamestore.api.controller;

import com.andreiradu.gamestore.api.model.Game;
import com.andreiradu.gamestore.api.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        Game game = gameService.getGameById(gameId);
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createGame(@RequestBody Game game) {
        gameService.createGames(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable Long gameId, @RequestBody Game updatedGame) {
        Game game = gameService.updateGame(gameId, updatedGame);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
