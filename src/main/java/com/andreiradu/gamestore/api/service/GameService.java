package com.andreiradu.gamestore.api.service;

import com.andreiradu.gamestore.api.exception.ResourceNotFoundException;
import com.andreiradu.gamestore.api.model.Game;
import com.andreiradu.gamestore.api.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("game not found with id: " + id));
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
        existingGame.setDescription(updatedGame.getDescription());
        existingGame.setPrice(updatedGame.getPrice());
        existingGame.setImage(updatedGame.getImage());
        existingGame.setReleaseDate(updatedGame.getReleaseDate());
        existingGame.setStockQuantity(updatedGame.getStockQuantity());
        return gameRepository.save(existingGame);
    }

    public void deleteGame(Long gameId) {
        getGameById(gameId);
        gameRepository.deleteById(gameId);
    }

    public Page<Game> findAllByPage(int pageNum, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        if (pageNum < 1) pageNum = 1;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<Game> page;
        if (keyword != null && !keyword.isEmpty()) {
            page = gameRepository.findAllByTitleContaining(keyword, pageable);
        } else {
            page = gameRepository.findAll(pageable);
        }
        return page;
    }
}
