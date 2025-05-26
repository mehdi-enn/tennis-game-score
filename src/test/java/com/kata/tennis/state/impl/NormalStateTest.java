package com.kata.tennis.state.impl;

import com.kata.tennis.domain.impl.ScoreBoard;
import com.kata.tennis.enums.Player;
import com.kata.tennis.service.GameService;
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

    @InjectMocks
    private NormalState normalState;

    @Test
    void endsGameIfPlayerReaches40AndOpponentHasLess() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(40);
        when(scoreBoard.getPoints(Player.B)).thenReturn(30);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(gameService).endGame(Player.A);
    }

    @Test
    void switchesToDeuceIfBothReach40() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(40);
        when(scoreBoard.getPoints(Player.B)).thenReturn(40);
        when(scoreBoard.bothAt(40)).thenReturn(true);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreBoard).setState(any(DeuceState.class));
        verify(gameService, never()).endGame(any());
    }

    @Test
    void incrementsPointsIfNoGameEnd() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(15);
        when(scoreBoard.getPoints(Player.B)).thenReturn(30);
        when(scoreBoard.bothAt(40)).thenReturn(false);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreBoard, never()).setState(any());
    }

    @Test
    void updatesToDeuceStateWhenBothAt40AfterPoint() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(30);
        when(scoreBoard.getPoints(Player.B)).thenReturn(40);
        when(scoreBoard.bothAt(40)).thenReturn(true);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
        verify(scoreBoard).setState(any(DeuceState.class));
    }

    @Test
    void doesNotDisplayScoreIfGameAlreadyOver() {
        when(scoreBoard.getPoints(Player.A)).thenReturn(15);
        when(scoreBoard.getPoints(Player.B)).thenReturn(30);
        when(scoreBoard.bothAt(40)).thenReturn(false);
        normalState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).incrementPoints(Player.A);
    }
}