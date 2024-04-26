package ru.mkustov.mtsspring.servises;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mkustov.mtsspring.animals.AbstractAnimal;
import ru.mkustov.mtsspring.config.RandomName;
import ru.mkustov.mtsspring.exceptions.InvalidAnimalBirtDateException;
import ru.mkustov.mtsspring.exceptions.SearchServiceImpl;
import ru.mkustov.mtsspring.interfaces.Animal;
import ru.mkustov.mtsspring.interfaces.AnimalRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;
@Service("animalList")
public class AnimalRepositoryImpl implements AnimalRepository {

    private final CreateAnimalServiceImpl createImpl;

    private Map<String, List<Animal>> animals;

    public AnimalRepositoryImpl(CreateAnimalServiceImpl createImpl) {
        this.createImpl = createImpl;

    }

    public Map<String, List<Animal>> getAnimals() {
        return animals;
    }

    @PostConstruct
    public void initService() throws IOException {
        createImpl.createAnimal(5);
        animals = createImpl.getAnimals();
    }
    @Override
    public Map<String, LocalDate> findLeapYearNames(List<AbstractAnimal> animals) {
        SearchServiceImpl searchService = new SearchServiceImpl();

        return animals.stream().filter(a -> {
            try {
                return searchService.checkLeapYearAnimal(a);
            } catch (InvalidAnimalBirtDateException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toMap(a -> a.getBreed() + " " + a.getName(), b -> {
            try {
                return b.getBirthDate();
            } catch (InvalidAnimalBirtDateException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<AbstractAnimal> animals, Integer N) {
        Map<Animal, Integer> map;
        animals = animals.stream()
                .sorted(Comparator.comparing(a -> {
                    try {
                        return a.getBirthDate();
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());
        map = animals.stream()
                .filter(a -> {
                    try {
                        return N < Math.toIntExact(YEARS.between(a.getBirthDate(), LocalDate.now()));
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toMap(a -> a, b -> {
                    try {
                        return Math.toIntExact(YEARS.between(b.getBirthDate(), LocalDate.now()));
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                })) ;

        if (map.isEmpty() && !animals.isEmpty()) {
            Integer firstYearsOld = null;
            try {
                firstYearsOld = Math.toIntExact(YEARS.between(animals.get(0).getBirthDate(), LocalDate.now()));
            } catch (InvalidAnimalBirtDateException e) {
                throw new RuntimeException(e);
            }
            map.put(animals.get(0), firstYearsOld);
        }
        //## дз. 3
        SaveToFile.serializeAnimalsToJson(map.keySet().stream().toList());
        return map;
    }

    @Override
    public Map<String, Integer> findDuplicate(List<AbstractAnimal> animals) {
        Map<String, Integer> map = new HashMap<>();
        for (AbstractAnimal animal : animals) {
            map.merge(animal.getBreed(), 1, (oldVal, newVal) -> oldVal + 1);
        }
        return map;
    }
    @Override
    public Map<String, List<Animal>> findDuplicateStreem(List<AbstractAnimal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::getBreed, Collectors.toList()));
    }

    @Override
    public void findAverageAge(List<AbstractAnimal> animals) {
        Double old = animals.stream()
                .mapToInt(a -> {
                    try {
                        return Math.toIntExact(YEARS.between(a.getBirthDate(), LocalDate.now()));
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                })
                .average()
                .orElse(0);
        System.out.println("Средний возраст животных: " + Double.toString(old)+ " лет");
    }

    @Override
    public List<Animal> findOldAndExpensive(List<AbstractAnimal> animals) {
        Double avg = animals.stream()
                .mapToDouble(a -> a.getCost())
                .average()
                .orElse(0);

        List<Animal> list = animals.stream()
                .filter(a -> {
                    try {
                        return Math.toIntExact(YEARS.between(a.getBirthDate(), LocalDate.now())) > 5;
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(a -> a.getCost() > avg)
                .sorted(Comparator.comparing(a -> {
                    try {
                        return a.getBirthDate();
                    } catch (InvalidAnimalBirtDateException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<String> findMinConstAnimals(List<AbstractAnimal> animals) {
        return animals.stream()
                .sorted((a, b) -> a.getCost().compareTo(b.getCost()))
                .limit(3)
                .map(a -> a.getName())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}

