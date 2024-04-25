package ru.mkustov.mtsspring.animals;

import org.springframework.stereotype.Component;

@Component("someShark")
public class Shark extends Predator {

    public Shark() {
        super();
        name = "Shark";
        breed = "Shark";
    }

    public Shark(String name) {
        super();
        this.name = name;
        this.breed = "Shark";
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretinfo='" + AbstractAnimal.decryptString(secretInfo) + '\'' +
                '}';
    }

}

