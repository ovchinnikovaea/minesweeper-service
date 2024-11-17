package ru.studiotg.minesweeperservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.studiotg.minesweeperservice.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameAlreadyCompletedException.class)
    public ResponseEntity<ErrorResponse> handleGameAlreadyCompletedException(GameAlreadyCompletedException ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCellCoordinatesException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCellCoordinatesException(InvalidCellCoordinatesException ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CellAlreadyOpenedException.class)
    public ResponseEntity<ErrorResponse> handleCellAlreadyOpenedException(CellAlreadyOpenedException ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(message);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
