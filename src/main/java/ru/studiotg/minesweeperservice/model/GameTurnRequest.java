package ru.studiotg.minesweeperservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GameTurnRequest {
    @Schema(description = "Идентификатор игры",
            name = "game_id",
            example = "01234567-89AB-CDEF-0123-456789ABCDEF",
            required = true)
    private String game_id;

    @Schema(description = "Колонка проверяемой ячейки (нумерация с нуля)",
            name = "col",
            example = "5",
            required = true)
    private Integer col;

    @Schema(description = "Ряд проверяемой ячейки (нумерация с нуля)",
            name = "row",
            example = "5",
            required = true)
    private Integer row;

}

