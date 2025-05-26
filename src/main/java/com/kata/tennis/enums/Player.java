package com.kata.tennis.enums;

public enum Player {
    A, B;

    public Player opponent() {
        return this == A ? B : A;
    }
}