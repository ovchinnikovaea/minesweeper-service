package ru.studiotg.minesweeperservice.model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class NewGameRequest {
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
}
