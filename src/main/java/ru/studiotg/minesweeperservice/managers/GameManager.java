package ru.studiotg.minesweeperservice.managers;

import ru.studiotg.minesweeperservice.model.GameInfoResponse;
import ru.studiotg.minesweeperservice.service.GameState;

public class GameManager {
    private final GameState gameState;
    private final GameFieldManager fieldManager;

    public GameManager(GameState gameState, GameFieldManager fieldManager) {
        this.gameState = gameState;
        this.fieldManager = fieldManager;
    }

    public GameInfoResponse getCurrentGameInfo(String gameId) {
        GameInfoResponse gameInfo = new GameInfoResponse();
        gameInfo.setGame_id(gameId);
        gameInfo.setWidth(gameState.getWidth());
        gameInfo.setHeight(gameState.getHeight());
        gameInfo.setMines_count(gameState.getMines_count());
        gameInfo.setCompleted(gameState.isCompleted());
        gameInfo.setField(fieldManager.getCurrentFieldView());
        return gameInfo;
    }
}
