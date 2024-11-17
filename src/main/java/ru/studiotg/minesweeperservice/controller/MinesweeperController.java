package ru.studiotg.minesweeperservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.studiotg.minesweeperservice.model.ErrorResponse;
import ru.studiotg.minesweeperservice.model.GameTurnRequest;
import ru.studiotg.minesweeperservice.model.NewGameRequest;
import ru.studiotg.minesweeperservice.model.GameInfoResponse;
import ru.studiotg.minesweeperservice.service.GameService;

@CrossOrigin(value = "https://minesweeper-test.studiotg.ru/")
@RestController
public class MinesweeperController {
    private final GameService gameService;

    public MinesweeperController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(description = "Начало новой игры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОК",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameInfoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса или некорректное действие",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/new")
    public ResponseEntity<?> addField(@RequestBody NewGameRequest gameRequest) {
        Object response = gameService.addNewGame(gameRequest);
        return ResponseEntity.ok(response);

    }

    @Operation(description = "Ход пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОК",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameInfoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса или некорректное действие",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/turn")
    public ResponseEntity<?> doTurn(@RequestBody GameTurnRequest turn) {
        Object response = gameService.openCell(turn);
        return ResponseEntity.ok(response);
    }

}
