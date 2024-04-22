package ru.mkustov.mtsspring;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("someWolf")
public class Wolf extends Predator {

    public Wolf() {
        //name = "Wolf";
        name = super.getName();
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
