package ru.studiotg.minesweeperservice.managers;

import ru.studiotg.minesweeperservice.Enums.Turn;
import ru.studiotg.minesweeperservice.service.GameState;

public class GameCellManager {
    private final GameState gameState;

    public GameCellManager(GameState gameState) {
        this.gameState = gameState;
    }

    public void openAllCellsAndRevealMines() {
        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                gameState.getOpened()[i][j] = true;
                if (gameState.getField()[i][j].equals(Turn.MINE.getSymbol())) {
                    gameState.getField()[i][j] = "X";
                }
            }
        }
    }

    public void openRemainingMines() {
        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (gameState.getField()[i][j].equals(Turn.MINE.getSymbol()) && !gameState.getOpened()[i][j]) {
                    gameState.getOpened()[i][j] = true;
                }
            }

        }
    }

    public void openCellRecursive(int row, int col) {
        if (isValidCell(row, col) || gameState.getOpened()[row][col]) {
            return;
        }
        gameState.getOpened()[row][col] = true;
        decrementOpenedCells();

        if (gameState.getOpenedCells() == gameState.getMines_count()) {
            gameState.setCompleted(true);
        }
        if (gameState.getField()[row][col].equals(Turn.ZERO.getSymbol())) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 || y != 0) {
                        openCellRecursive(row + x, col + y);
                    }
                }
            }
        }
    }

    public void decrementOpenedCells() {
        if (gameState.getOpenedCells() > 0) {
            gameState.setOpenedCells(gameState.getOpenedCells() - 1);
        }
    }


    public boolean isValidCell(int row, int col) {
        return row < 0 || row >= gameState.getHeight() || col < 0 || col >= gameState.getWidth();
    }

}
