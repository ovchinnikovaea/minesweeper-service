package ru.studiotg.minesweeperservice.service;

import ru.studiotg.minesweeperservice.model.GameInfoResponse;
import ru.studiotg.minesweeperservice.model.GameTurnRequest;
import ru.studiotg.minesweeperservice.model.NewGameRequest;

public interface GameService {
    GameInfoResponse addNewGame(NewGameRequest gameRequest);

    GameInfoResponse openCell(GameTurnRequest gameTurn);

}