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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvantageStateTest {
    @Mock
    private ScoreBoard scoreBoard;

    @Mock
    private GameService gameService;

    @Mock
    private ScoreOutput scoreOutput;

    @InjectMocks
    private AdvantageState advantageState;

    @Test
    void endsGameIfAdvantagePlayerScores() {
        when(scoreBoard.getAdvantagePlayer()).thenReturn(Player.A);
        advantageState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(gameService).endGame(Player.A);
    }

    @Test
    void returnsToDeuceIfOpponentScores() {
        when(scoreBoard.getAdvantagePlayer()).thenReturn(Player.B);
        when(gameService.getScoreOutput()).thenReturn(scoreOutput);
        advantageState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).setState(any(DeuceState.class));
        verify(scoreBoard).setAdvantagePlayer(null);
        verify(gameService.getScoreOutput()).displayScore(scoreBoard);
    }
}