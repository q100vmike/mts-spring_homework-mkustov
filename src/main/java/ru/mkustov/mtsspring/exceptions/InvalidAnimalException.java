package ru.mkustov.mtsspring.exceptions;

public class InvalidAnimalException extends Error {
    public InvalidAnimalException(String message) {
        super(message);
    }
}
