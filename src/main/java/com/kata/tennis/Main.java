package com.kata.tennis;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.service.impl.TennisGame;
import com.kata.tennis.view.impl.ScoreDisplay;

public class Main {
    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();
        ScoreDisplay scoreOutput = new ScoreDisplay(System.out);
        TennisGame tennisGame = new TennisGame(scoreBoard, scoreOutput);
        tennisGame.processPoints("ABABABAA");
    }
}