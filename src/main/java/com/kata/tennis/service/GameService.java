package com.kata.tennis.service;

import com.kata.tennis.enums.Player;
import com.kata.tennis.view.ScoreOutput;

public interface GameService {
    void endGame(Player winner);

    ScoreOutput getScoreOutput();
}