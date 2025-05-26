package com.kata.tennis.view;

import com.kata.tennis.domain.ScoreModel;
import com.kata.tennis.enums.Player;

public interface ScoreOutput {
    void displayScore(ScoreModel scoreModel);

    void printWinner(Player winner);
}