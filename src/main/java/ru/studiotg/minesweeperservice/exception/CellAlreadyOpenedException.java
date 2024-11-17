package ru.studiotg.minesweeperservice.exception;

public class CellAlreadyOpenedException extends RuntimeException {
    public CellAlreadyOpenedException(String message) {
        super(message);
    }
}
