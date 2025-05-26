package com.kata.tennis.view.impl;

import com.kata.tennis.domain.ScoreModel;
import com.kata.tennis.enums.Player;
import com.kata.tennis.view.ScoreOutput;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

@RequiredArgsConstructor
public class ScoreDisplay implements ScoreOutput {
    private final PrintStream out;

    @Override
    public void displayScore(ScoreModel scoreModel) {
        switch (scoreModel.getState().getClass().getSimpleName()) {
            case "DeuceState" -> out.println("Deuce");
            case "AdvantageState" -> printAdvantage(scoreModel);
            case "NormalState" -> printNormal(scoreModel);
        }
    }

    @Override
    public void printWinner(Player winner) {
        out.println("Player " + winner + " wins the game");
    }

    private void printNormal(ScoreModel scoreModel) {
        out.printf("Player A : %s / Player B : %s%n",
                format(scoreModel.getPoints(Player.A)), format(scoreModel.getPoints(Player.B)));
    }

    private void printAdvantage(ScoreModel scoreModel) {
        if (scoreModel.getAdvantagePlayer() == Player.A) {
            out.println("Player A : advantage / Player B : 40");
        } else {
            out.println("Player A : 40 / Player B : advantage");
        }
    }

    private String format(int points) {
        return switch (points) {
            case 0, 15, 30, 40 -> String.valueOf(points);
            default -> "";
        };
    }
}
