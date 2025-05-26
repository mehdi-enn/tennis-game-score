package com.kata.tennis.service.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TennisGame implements GameService {
    private final ScoreBoard scoreBoard;

    public void processPoints(String input) {
        for (char c : input.toCharArray()) {
            if (scoreBoard.isGameOver())
                break;
            Player player = Player.valueOf(String.valueOf(c));
            scoreBoard.getState().pointWonBy(player, scoreBoard, this);
        }
    }

    @Override
    public void endGame(Player winner) {
        scoreBoard.setGameOver(true);
    }
}