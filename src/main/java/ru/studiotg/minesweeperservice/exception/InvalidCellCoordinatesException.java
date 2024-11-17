package ru.studiotg.minesweeperservice.exception;

public class InvalidCellCoordinatesException extends RuntimeException {
    public InvalidCellCoordinatesException(String message) {
        super(message);
    }
}