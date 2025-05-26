package com.kata.tennis.state.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;
import com.kata.tennis.state.GameState;

public class NormalState implements GameState {
    @Override
    public void pointWonBy(Player player, ScoreBoard scoreBoard, GameService gameService) {
        int scorerPoints = scoreBoard.getPoints(player);
        int opponentPoints = scoreBoard.getPoints(player.opponent());

        if (scorerPoints == 40 && opponentPoints < 40) {
            gameService.endGame(player);
        } else {
            scoreBoard.incrementPoints(player);
            if (scoreBoard.bothAt(40)) {
                scoreBoard.setState(new DeuceState());
            }
        }
        if (!scoreBoard.isGameOver()) {
            gameService.getScoreOutput().displayScore(scoreBoard);
        }
    }
}