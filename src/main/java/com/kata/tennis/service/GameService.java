package com.kata.tennis.service;

import com.kata.tennis.enums.Player;

public interface GameService {
    void endGame(Player winner);
}