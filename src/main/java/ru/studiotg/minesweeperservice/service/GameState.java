package ru.studiotg.minesweeperservice.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GameState {

    private int height;
    private int width;
    private int mines_count;
    private String[][] field;
    private boolean[][] opened;
    private int openedCells;
    private boolean isCompleted = false;

    public GameState(int height, int width, int mines_count) {
        this.height = height;
        this.width = width;
        this.mines_count = mines_count;
        this.field = new String[height][width];
        this.opened = new boolean[height][width];
        this.openedCells = height * width;
        this.isCompleted = false;
    }
}
