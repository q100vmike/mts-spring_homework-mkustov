package ru.mkustov.mtsspring.servises;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.mkustov.mtsspring.animals.AbstractAnimal;
import ru.mkustov.mtsspring.interfaces.Animal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class SaveToFile {
    private static Path dataPath = Paths.get("src", "main", "resources", "animals", "logData.txt");
    private static Path secPath = Paths.get("src", "main", "resources", "secretStore", "secretInformation.txt");
    private static Path jsnPath = Paths.get("src", "main", "resources", "results", "findOlderAnimals.json");

    private String secretInformation;
    public static void saveAmimal(AbstractAnimal abstractAnimal) throws IOException {
        String line = "";
        List<String> list = new ArrayList<>();

        if (Files.exists(dataPath)) {
            try {
                list = Files.readAllLines(dataPath);
                line =  list.size() + 1 + ". " + abstractAnimal.toFileString() + "\n";
                Files.write(dataPath, line.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }
    public static void saveAnimalByExternalizable(AbstractAnimal abstractAnimal) throws IOException {
        FileOutputStream fileOutputStream
                = new FileOutputStream(String.valueOf(dataPath));
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        abstractAnimal.writeExternal(objectOutputStream);

        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public static void serializeAnimalsToJson(List<Animal> animals) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(new File(String.valueOf(jsnPath)), animals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
