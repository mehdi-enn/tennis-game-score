package com.kata.tennis.state.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;
import com.kata.tennis.state.GameState;

public class AdvantageState implements GameState {
    @Override
    public void pointWonBy(Player player, ScoreBoard scoreBoard, GameService gameService) {
        if (scoreBoard.getAdvantagePlayer() == player) {
            gameService.endGame(player);
        } else {
            scoreBoard.setState(new DeuceState());
            scoreBoard.setAdvantagePlayer(null);
            gameService.getScoreOutput().displayScore(scoreBoard);
        }
    }
}