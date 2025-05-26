package com.kata.tennis.domain.impl;

import com.kata.tennis.domain.ScoreModel;
import com.kata.tennis.enums.Player;
import lombok.Data;

import java.util.EnumMap;

@Data
public class ScoreBoard implements ScoreModel {
    private final EnumMap<Player, Integer> points = new EnumMap<>(Player.class);
    private Player advantagePlayer;
    private boolean gameOver = false;

    public ScoreBoard() {
        points.put(Player.A, 0);
        points.put(Player.B, 0);
    }

    public int getPoints(Player player) {
        return points.get(player);
    }

    public void setPoints(Player player, int value) {
        points.put(player, value);
    }

    public void incrementPoints(Player player) {
        int current = getPoints(player);
        int next = switch (current) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            default -> current;
        };
        setPoints(player, next);
    }

    public boolean bothAt(int value) {
        return getPoints(Player.A) == value && getPoints(Player.B) == value;
    }
}