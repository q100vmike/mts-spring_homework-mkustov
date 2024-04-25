package ru.mkustov.mtsspring.interfaces;

import ru.mkustov.mtsspring.animals.AbstractAnimal;
import ru.mkustov.mtsspring.exceptions.InvalidAnimalBirtDateException;
import ru.mkustov.mtsspring.exceptions.InvalidAnimalException;

public interface SearchService {
    boolean checkLeapYearAnimal(AbstractAnimal animal) throws InvalidAnimalException, InvalidAnimalBirtDateException;
}
