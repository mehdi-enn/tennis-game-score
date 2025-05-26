package com.kata.tennis.view.impl;

import com.kata.tennis.domain.ScoreModel;
import com.kata.tennis.enums.Player;
import com.kata.tennis.state.impl.AdvantageState;
import com.kata.tennis.state.impl.DeuceState;
import com.kata.tennis.state.impl.NormalState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreDisplayTest {
    private ByteArrayOutputStream outContent;
    private ScoreDisplay scoreDisplay;

    @Mock
    ScoreModel scoreModel;

    @BeforeEach
    void setup() {
        outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        scoreDisplay = new ScoreDisplay(printStream);
    }

    @Test
    void displaysScoreWithUnknownPoints() {
        when(scoreModel.getState()).thenReturn(new NormalState());
        when(scoreModel.getPoints(Player.A)).thenReturn(16);
        when(scoreModel.getPoints(Player.B)).thenReturn(42);
        scoreDisplay.displayScore(scoreModel);
        assertEquals("Player A :  / Player B : \n", outContent.toString());
    }


    @Test
    void displaysDeuce() {
        when(scoreModel.getState()).thenReturn(new DeuceState());
        scoreDisplay.displayScore(scoreModel);
        assertEquals("Deuce\n", outContent.toString());
    }


    @Test
    void displaysAdvantagePlayerA() {
        when(scoreModel.getState()).thenReturn(new AdvantageState());
        when(scoreModel.getAdvantagePlayer()).thenReturn(Player.A);
        scoreDisplay.displayScore(scoreModel);
        assertEquals("Player A : advantage / Player B : 40\n", outContent.toString());
    }

    @Test
    void displaysAdvantagePlayerB() {
        when(scoreModel.getState()).thenReturn(new AdvantageState());
        when(scoreModel.getAdvantagePlayer()).thenReturn(Player.B);
        scoreDisplay.displayScore(scoreModel);
        assertEquals("Player A : 40 / Player B : advantage\n", outContent.toString());
    }


    @Test
    void displaysNormal() {
        when(scoreModel.getState()).thenReturn(new NormalState());
        when(scoreModel.getPoints(Player.A)).thenReturn(15);
        when(scoreModel.getPoints(Player.B)).thenReturn(30);
        scoreDisplay.displayScore(scoreModel);
        assertEquals("Player A : 15 / Player B : 30\n", outContent.toString());
    }

    @Test
    void printsCorrectly() {
        scoreDisplay.printWinner(Player.B);
        assertEquals("Player B wins the game\n", outContent.toString());
    }
}