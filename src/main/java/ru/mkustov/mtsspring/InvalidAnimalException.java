package ru.mkustov.mtsspring;

public class InvalidAnimalException extends Error {
    public InvalidAnimalException(String message) {
        super(message);
    }
}
