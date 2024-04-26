package ru.mkustov.mtsspring.servises;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.mkustov.mtsspring.animals.*;
import ru.mkustov.mtsspring.config.RandomName;
import ru.mkustov.mtsspring.interfaces.Animal;
import ru.mkustov.mtsspring.interfaces.CreateAnimalService;

import java.io.IOException;
import java.util.*;

@Service("createAnimalList")
public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Value("${animals.list.default}")
    public int val;

    @Value("${animals.list.names}")
   // @Value("#{${animal.list.names}}")
    public List<String> names;

    public Map<String, List<Animal>> animals;

    @Autowired
    private RandomName randomName;

    public Map<String, List<Animal>> getAnimals() {
        return animals;
    }

    public Map<String, List<Animal>> createAnimal(@Value("${animals.list.default}") int n) throws IOException {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        Random  r = new Random();
        List<Animal> animal = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {

            String name = randomName.getRandomName();

            int animalType = r.nextInt(5);
            switch (animalType) {
                case 0:
                    animal.add(new Cat(name));
                    break;
                case 1:
                    animal.add(new Dog(name));
                    break;
                case 2:
                    animal.add(new Shark(name));
                    break;
                default:
                    animal.add(new Wolf(name));
            }
            String animalName = animal.get(i).getName();
            if (!animalsMap.containsKey(animalName)) {
                animalsMap.put(animalName, new ArrayList<>());
            }
            animalsMap.get(animalName).add(animal.get(i));
            System.out.println(animal.get(i));
            //## дз 1 use custom
            SaveToFile.saveAmimal((AbstractAnimal) animal.get(i));
        }
        animals = animalsMap;
        return animalsMap;
    }

    @Override
    public Map<String, List<Animal>> createAnimal() throws IOException {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> animal = new ArrayList<>(10);
        Random  r = new Random();
        int i = 0;
        do {
            int animalType = r.nextInt(3);
            switch (animalType) {
                case 0:
                    animal.add(new Cat());
                    break;
                case 1:
                    animal.add(new Dog());
                    break;
                case 2:
                    animal.add(new Shark());
                    break;
                default:
                    animal.add(new Wolf());
            }
            String animalName = animal.get(i).getName();
            if (!animalsMap.containsKey(animalName)) {
                animalsMap.put(animalName, new ArrayList<>());
            }
            animalsMap.get(animalName).add(animal.get(i));
            System.out.println(animal.get(i));
            //## дз 1 use Externalizable
            SaveToFile.saveAnimalByExternalizable((AbstractAnimal) animal.get(i));
            i++;
        } while (i < animal.size());

        return animalsMap;
    }
}