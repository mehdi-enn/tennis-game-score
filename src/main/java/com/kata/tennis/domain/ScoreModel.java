package com.kata.tennis.domain;

import com.kata.tennis.enums.Player;

public interface ScoreModel {
    int getPoints(Player player);

    void setPoints(Player player, int value);

    void incrementPoints(Player player);

    boolean bothAt(int value);

    Player getAdvantagePlayer();

    void setAdvantagePlayer(Player player);

    boolean isGameOver();

    void setGameOver(boolean gameOver);
}