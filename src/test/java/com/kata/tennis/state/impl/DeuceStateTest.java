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
class DeuceStateTest {
    @Mock
    private ScoreBoard scoreBoard;

    @Mock
    private GameService gameService;

    @Mock
    private ScoreOutput scoreOutput;

    @InjectMocks
    private DeuceState deuceState;

    @Test
    void setsAdvantagePlayer() {
        when(gameService.getScoreOutput()).thenReturn(scoreOutput);
        deuceState.pointWonBy(Player.A, scoreBoard, gameService);
        verify(scoreBoard).setAdvantagePlayer(Player.A);
        verify(scoreBoard).setState(any(AdvantageState.class));
        verify(scoreOutput).displayScore(scoreBoard);
    }
}