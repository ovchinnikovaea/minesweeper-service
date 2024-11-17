package ru.studiotg.minesweeperservice.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GameInfoResponse {
    @Schema(description = "Идентификатор игры",
            name = "game_id",
            example = "01234567-89AB-CDEF-0123-456789ABCDEF",
            required = true)
    private String game_id;

    @Schema(description = "Ширина игрового поля",
            name = "width",
            type = "integer",
            example = "10",
            required = true)
    private Integer width;

    @Schema(description = "Высота игрового поля",
            name = "height",
            type = "integer",
            example = "10",
            required = true)
    private Integer height;

    @Schema(description = "Количество мин на поле",
            name = "mines_count",
            type = "integer",
            example = "10",
            required = true)
    private Integer mines_count;

    @Schema(description = "Завершена ли игра",
            name = "completed",
            type = "boolean",
            example = "false")
    private Boolean completed;

    @ArraySchema(
            arraySchema = @Schema(description = "Строки минного поля (количество равно высоте height)"),
            schema = @Schema(
                    description = "Столбцы минного поля (количество равно ширине width)",
                    type = "string",
                    allowableValues = {" ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "M", "X"},
                    required = true
            )
    )
    private String[][] field;

}
