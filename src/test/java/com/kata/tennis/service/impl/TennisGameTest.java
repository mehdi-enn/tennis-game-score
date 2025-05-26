package com.kata.tennis.service.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.state.GameState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TennisGameTest {
    @Mock
    private ScoreBoard scoreBoard;

    @Mock
    private GameState gameState;

    @InjectMocks
    private TennisGame tennisGame;

    @Test
    void stopsAtGameOver() {
        when(scoreBoard.getState()).thenReturn(gameState);
        when(scoreBoard.isGameOver()).thenReturn(false, false, true);
        tennisGame.processPoints("AB");
        verify(gameState, times(2)).pointWonBy(any(), eq(scoreBoard), eq(tennisGame));
    }

    @Test
    void endsGame() {
        Player winner = Player.A;
        tennisGame.endGame(winner);
        verify(scoreBoard).setGameOver(true);
    }

    @Test
    void ignoresPointsIfGameOver() {
        when(scoreBoard.isGameOver()).thenReturn(true);
        tennisGame.processPoints("AB");
        verify(gameState, never()).pointWonBy(any(), any(), any());
    }
}