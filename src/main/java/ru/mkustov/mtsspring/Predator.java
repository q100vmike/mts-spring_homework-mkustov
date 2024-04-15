package ru.mkustov.mtsspring;

import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal{
    public Predator(String breed, String name, Double cost, String character, String secretInfo, LocalDate birthDate) {
        super(breed, name, cost, character, secretInfo, birthDate);
    }

    public Predator() {

    }
}
