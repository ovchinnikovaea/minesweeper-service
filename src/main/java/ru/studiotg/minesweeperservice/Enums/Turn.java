package ru.studiotg.minesweeperservice.Enums;

public enum Turn {
    EMPTY(" "),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    MINE("M"),
    FLAG("X");

    private final String symbol;

    Turn(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
