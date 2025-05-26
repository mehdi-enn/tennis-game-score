package com.kata.tennis.domain;

import com.kata.tennis.enums.Player;
import com.kata.tennis.state.GameState;

public interface ScoreModel {
    int getPoints(Player player);

    void setPoints(Player player, int value);

    void incrementPoints(Player player);

    boolean bothAt(int value);

    GameState getState();

    void setState(GameState state);

    Player getAdvantagePlayer();

    void setAdvantagePlayer(Player player);

    boolean isGameOver();

    void setGameOver(boolean gameOver);
}