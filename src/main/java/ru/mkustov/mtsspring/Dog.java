package ru.mkustov.mtsspring;

public class Dog extends Pet {
    public Dog() {
        super();
        name = "Dog";
        breed = "Dog";
    }
    public Dog(String name) {
        super();
        this.name = name;
        this.breed = "Dog";
    }
    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretinfo='" + AbstractAnimal.decryptString(secretInfo) + '\'' +
                '}';
    }

}
