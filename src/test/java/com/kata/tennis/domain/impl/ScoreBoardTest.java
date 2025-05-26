package com.kata.tennis.domain.impl;

import com.kata.tennis.enums.Player;
import com.kata.tennis.state.GameState;
import com.kata.tennis.state.impl.DeuceState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @BeforeEach
    void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void startsAtZero() {
        assertEquals(0, scoreBoard.getPoints(Player.A));
        assertEquals(0, scoreBoard.getPoints(Player.B));
    }

    @Test
    void setsPoints() {
        scoreBoard.setPoints(Player.A, 15);
        assertEquals(15, scoreBoard.getPoints(Player.A));
    }

    @Test
    void incrementsPoints() {
        scoreBoard.incrementPoints(Player.A);
        assertEquals(15, scoreBoard.getPoints(Player.A));
        scoreBoard.incrementPoints(Player.A);
        assertEquals(30, scoreBoard.getPoints(Player.A));
        scoreBoard.incrementPoints(Player.A);
        assertEquals(40, scoreBoard.getPoints(Player.A));
        scoreBoard.incrementPoints(Player.A);
        assertEquals(40, scoreBoard.getPoints(Player.A));
    }

    @Test
    void bothAtTrue() {
        scoreBoard.setPoints(Player.A, 30);
        scoreBoard.setPoints(Player.B, 30);
        assertTrue(scoreBoard.bothAt(30));
    }

    @Test
    void bothAtFalse() {
        scoreBoard.setPoints(Player.A, 30);
        scoreBoard.setPoints(Player.B, 15);
        assertFalse(scoreBoard.bothAt(30));
    }

    @Test
    void setsAdvantage() {
        scoreBoard.setAdvantagePlayer(Player.B);
        assertEquals(Player.B, scoreBoard.getAdvantagePlayer());
    }

    @Test
    void setsGameOver() {
        assertFalse(scoreBoard.isGameOver());
        scoreBoard.setGameOver(true);
        assertTrue(scoreBoard.isGameOver());
    }

    @Test
    void getsSetState() {
        GameState deuceState = new DeuceState();
        scoreBoard.setState(deuceState);
        assertEquals(deuceState, scoreBoard.getState());
    }
}