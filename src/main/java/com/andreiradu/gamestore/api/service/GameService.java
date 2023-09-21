package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.model.Game;
import com.andreiradu.gamestore.api.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game getGameById(Long id) {
        return gameRepository.findById(id).get();
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void createGames(Game game) {
        gameRepository.save(game);
    }

    public Game updateGame(Long gameId, Game updatedGame) {
        Game existingGame = getGameById(gameId);
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setPlatform(updatedGame.getPlatform());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setPrice(updatedGame.getPrice());
        existingGame.setImage(updatedGame.getImage());
        existingGame.setStockQuantity(updatedGame.getStockQuantity());
        existingGame.setReleaseDate(updatedGame.getReleaseDate());
        return gameRepository.save(existingGame);
    }

    public void deleteGame(Long gameId) {
        getGameById(gameId);
        gameRepository.deleteById(gameId);
    }
}
