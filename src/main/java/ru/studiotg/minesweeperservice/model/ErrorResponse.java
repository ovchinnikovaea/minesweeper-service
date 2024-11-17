package ru.studiotg.minesweeperservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse {
    @Schema(description = "Описание ошибки",
            name = "error",
            type = "string",
            example = "Произошла непредвиденная ошибка",
            required = false)
    private String error;
}
