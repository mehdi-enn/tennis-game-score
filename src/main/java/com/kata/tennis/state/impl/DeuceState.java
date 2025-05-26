package com.kata.tennis.state.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;
import com.kata.tennis.state.GameState;

public class DeuceState implements GameState {
    @Override
    public void pointWonBy(Player player, ScoreBoard scoreBoard, GameService gameService) {
        scoreBoard.setAdvantagePlayer(player);
        scoreBoard.setState(new AdvantageState());
    }
}