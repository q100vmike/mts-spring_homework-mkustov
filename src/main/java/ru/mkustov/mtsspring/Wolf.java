package ru.mkustov.mtsspring;

public class Wolf extends Predator {
    public Wolf() {
        name = "Wolf";
        breed = "Wolf";
    }
    public Wolf(String name) {
        this.name = name;
        this.breed = "Wolf";
    }
    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretinfo='" + AbstractAnimal.decryptString(secretInfo) + '\'' +
                '}';
    }

}
