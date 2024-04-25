package ru.mkustov.mtsspring.interfaces;

import ru.mkustov.mtsspring.exceptions.InvalidAnimalBirtDateException;
import ru.mkustov.mtsspring.exceptions.InvalidAnimalException;

import java.time.LocalDate;

public interface Animal {
    String getBreed();
    String getName();
    Double getCost();
    String getCharacter();
    LocalDate getBirthDate() throws InvalidAnimalException, InvalidAnimalBirtDateException;
    String getSecretInfomation();
    String getType();
}