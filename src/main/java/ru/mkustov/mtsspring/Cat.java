package ru.mkustov.mtsspring;

public class Cat extends Pet {
    public Cat() {
        name = "Cat";
        breed = "Cat";
    }
    public Cat(String name) {
        this.name = name;
        this.breed = "Cat";
    }
    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretinfo='" + AbstractAnimal.decryptString(secretInfo) + '\'' +
                '}';
    }
}
