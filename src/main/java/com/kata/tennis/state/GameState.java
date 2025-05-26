package com.kata.tennis.state;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;

public interface GameState {
    void pointWonBy(Player player, ScoreBoard scoreBoard, GameService gameService);
}