package com.kata.tennis.state.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;
import com.kata.tennis.view.ScoreOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NormalStateTest {
    @Mock
    private ScoreBoard scoreBoard;

    @Mock
    private GameService gameService;

    @Mock
    private ScoreOutput scoreOutput;

    @InjectMocks
    private NormalState normalState;

    @Test
    void endsGameIfPlayerReaches40AndOpponentHasLess() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(40);
        when(scoreBoard.getPoints(Player.B)).thenReturn(30);
        when(scoreBoard.isGameOver()).thenReturn(true);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(gameService).endGame(Player.A);
    }

    @Test
    void switchesToDeuceIfBothReach40() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(40);
        when(scoreBoard.getPoints(Player.B)).thenReturn(40);
        when(scoreBoard.bothAt(40)).thenReturn(true);
        when(scoreBoard.isGameOver()).thenReturn(false);
        when(gameService.getScoreOutput()).thenReturn(scoreOutput);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreBoard).setState(any(DeuceState.class));
        verify(scoreOutput).displayScore(scoreBoard);
        verify(gameService, never()).endGame(any());
    }


    @Test
    void incrementsPointsIfNoGameEnd() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(15);
        when(scoreBoard.getPoints(Player.B)).thenReturn(30);
        when(scoreBoard.bothAt(40)).thenReturn(false);
        when(scoreBoard.isGameOver()).thenReturn(false);
        when(gameService.getScoreOutput()).thenReturn(scoreOutput);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreBoard, never()).setState(any());
        verify(scoreOutput).displayScore(scoreBoard);
    }

    @Test
    void updatesToDeuceStateWhenBothAt40AfterPoint() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(30);
        when(scoreBoard.getPoints(Player.B)).thenReturn(40);
        when(scoreBoard.bothAt(40)).thenReturn(true);
        when(scoreBoard.isGameOver()).thenReturn(false);
        when(gameService.getScoreOutput()).thenReturn(scoreOutput);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreBoard).setState(any(DeuceState.class));
        verify(scoreOutput).displayScore(scoreBoard);
    }

    @Test
    void doesNotDisplayScoreIfGameAlreadyOver() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(15);
        when(scoreBoard.getPoints(Player.B)).thenReturn(30);
        when(scoreBoard.bothAt(40)).thenReturn(false);
        when(scoreBoard.isGameOver()).thenReturn(true);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreOutput, never()).displayScore(any());
    }
}