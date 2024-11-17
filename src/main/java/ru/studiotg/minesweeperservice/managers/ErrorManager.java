package ru.studiotg.minesweeperservice.managers;

import ru.studiotg.minesweeperservice.model.ErrorResponse;

public class ErrorManager {

    public ErrorResponse createErrorResponse(String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(errorMessage);
        return errorResponse;
    }
}
