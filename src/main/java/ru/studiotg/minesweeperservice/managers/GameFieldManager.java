package ru.studiotg.minesweeperservice.managers;

import ru.studiotg.minesweeperservice.Enums.Turn;
import ru.studiotg.minesweeperservice.service.GameState;

import java.util.Random;

public class GameFieldManager {
    private final GameState gameState;

    public GameFieldManager(GameState gameState) {
        this.gameState = gameState;
    }

    public void addEmptyField() {
        String[][] field = gameState.getField();
        boolean[][] opened = gameState.getOpened();
        int openedCells = gameState.getOpenedCells();
        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                field[i][j] = Turn.ZERO.getSymbol();
                opened[i][j] = false;
            }
        }
    }

    public String[][] getCurrentFieldView() {
        String[][] fieldView = new String[gameState.getHeight()][gameState.getWidth()];
        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (gameState.getOpened()[i][j]) {
                    fieldView[i][j] = gameState.getField()[i][j];
                } else {
                    fieldView[i][j] = Turn.EMPTY.getSymbol();
                }
            }
        }
        return fieldView;
    }


    public void placeMines() {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < gameState.getMines_count()) {
            int row = random.nextInt(gameState.getHeight());
            int col = random.nextInt(gameState.getWidth());

            if (!gameState.getField()[row][col].equals(Turn.MINE.getSymbol())) {
                gameState.getField()[row][col] = Turn.MINE.getSymbol();
                placedMines++;
            }
        }
    }

    public void placeNumbers() {

        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (!gameState.getField()[i][j].equals(Turn.MINE.getSymbol())) {
                    int count = getCount(i, j);
                    if (count > 0) {
                        gameState.getField()[i][j] = Turn.values()[count + 1].getSymbol();
                    }
                }
            }
        }
    }

    private int getCount(int i, int j) {
        int count = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int nx = i + x;
                int ny = j + y;
                if ((x != 0 || y != 0) &&
                        isValidCell(nx, ny) &&
                        gameState.getField()[nx][ny].equals(Turn.MINE.getSymbol())) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < gameState.getHeight() && col >= 0 && col < gameState.getWidth();
    }
}
