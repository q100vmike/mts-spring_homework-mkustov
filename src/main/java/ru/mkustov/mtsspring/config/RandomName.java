package ru.mkustov.mtsspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component("rndname")
public class RandomName {

    private String randomName;

    @Value("#{${animal.list.names}}")
    private List<String> randomNames;

    public RandomName() {
    }

    public String getRandomName() {
        Random r = new Random();
        int index = r.nextInt(randomNames.size());
        return randomNames.get(index);
    }
}
