package ru.mkustov.mtsspring;

public interface SearchService {
    boolean checkLeapYearAnimal(AbstractAnimal animal) throws InvalidAnimalException, InvalidAnimalBirtDateException;
}
