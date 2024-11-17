package ru.studiotg.minesweeperservice.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.studiotg.minesweeperservice.Enums.Turn;
import ru.studiotg.minesweeperservice.exception.CellAlreadyOpenedException;
import ru.studiotg.minesweeperservice.exception.GameAlreadyCompletedException;
import ru.studiotg.minesweeperservice.exception.InvalidCellCoordinatesException;
import ru.studiotg.minesweeperservice.managers.ErrorManager;
import ru.studiotg.minesweeperservice.managers.GameCellManager;
import ru.studiotg.minesweeperservice.managers.GameFieldManager;
import ru.studiotg.minesweeperservice.managers.GameManager;
import ru.studiotg.minesweeperservice.model.GameInfoResponse;
import ru.studiotg.minesweeperservice.model.GameTurnRequest;
import ru.studiotg.minesweeperservice.model.NewGameRequest;
import ru.studiotg.minesweeperservice.service.*;

import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    private GameState gameState;
    private GameFieldManager fieldManager;
    private GameCellManager cellManager;

    private GameManager gameManager;

    private ErrorManager errorManager;

    @Override
    public GameInfoResponse addNewGame(NewGameRequest gameRequest) {
        this.gameState = new GameState(gameRequest.getHeight(), gameRequest.getWidth(), gameRequest.getMines_count());
        this.fieldManager = new GameFieldManager(gameState);
        this.cellManager = new GameCellManager(gameState);
        this.gameManager = new GameManager(gameState, fieldManager);
        this.errorManager = new ErrorManager();

        GameInfoResponse gameInfo = new GameInfoResponse();
        String gameId = UUID.randomUUID().toString();
        gameInfo.setGame_id(gameId);
        gameInfo.setWidth(gameState.getWidth());
        gameInfo.setHeight(gameState.getHeight());
        gameInfo.setMines_count(gameState.getMines_count());
        gameInfo.setCompleted(false);

        fieldManager.addEmptyField();
        fieldManager.placeMines();
        fieldManager.placeNumbers();

        gameInfo.setField(fieldManager.getCurrentFieldView());

        return gameInfo;
    }

    public GameInfoResponse openCell(GameTurnRequest gameTurn) {

        int row = gameTurn.getRow();
        int col = gameTurn.getCol();

        if (gameState.isCompleted()) {
            throw new GameAlreadyCompletedException("Игра завершена, новые ходы недопустимы");
        }

        if (!fieldManager.isValidCell(row, col)) {
            throw new InvalidCellCoordinatesException("Неправильные координаты ячейки");
        }

        if (gameState.getOpened()[row][col]) {
            throw new CellAlreadyOpenedException("Ячейка уже открыта");
        }

        if (gameState.getField()[row][col].equals(Turn.MINE.getSymbol())) {
            cellManager.openAllCellsAndRevealMines();
            gameState.setCompleted(true);
        } else {
            cellManager.openCellRecursive(row, col);
            if (gameState.getOpenedCells() == gameState.getMines_count()) {
                cellManager.openRemainingMines();
                gameState.setCompleted(true);
            }
        }
        return gameManager.getCurrentGameInfo(gameTurn.getGame_id());
    }
}

