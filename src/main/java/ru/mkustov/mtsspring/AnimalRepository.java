package ru.mkustov.mtsspring;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {
    Map<String, LocalDate> findLeapYearNames(List<AbstractAnimal> animals) throws InvalidAnimalBirtDateException;

    Map<Animal,Integer> findOlderAnimal(List<AbstractAnimal> animals, Integer N);

    Map<String, Integer> findDuplicate(List<AbstractAnimal> animals);

    Map<String, List<Animal>> findDuplicateStreem(List<AbstractAnimal> animals);

    void findAverageAge(List<AbstractAnimal> animals);

    List<Animal> findOldAndExpensive(List<AbstractAnimal> animals);

    List<String> findMinConstAnimals(List<AbstractAnimal> animals);
}
