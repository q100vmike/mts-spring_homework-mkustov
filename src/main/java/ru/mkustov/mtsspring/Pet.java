package ru.mkustov.mtsspring;

import java.time.LocalDate;

public abstract class Pet extends AbstractAnimal {
    public Pet(String breed, String name, Double cost, String character, String secretInfo, LocalDate birthDate) {
        super(breed, name, cost, character, secretInfo, birthDate);
    }

    public Pet() {
        super();
    }
}

