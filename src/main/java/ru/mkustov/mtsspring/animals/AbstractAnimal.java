package ru.mkustov.mtsspring.animals;


import com.fasterxml.jackson.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import ru.mkustov.mtsspring.exceptions.InvalidAnimalBirtDateException;
import ru.mkustov.mtsspring.interfaces.Animal;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "Dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "Cat"),
        @JsonSubTypes.Type(value = Shark.class, name = "Shark"),
        @JsonSubTypes.Type(value = Wolf.class, name = "Wolf"),
}
)
public abstract class AbstractAnimal implements Animal, Externalizable {
    protected String breed;
    protected String name;

    protected Double cost;
    protected String character;
    protected String secretInfo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate birthDate;

    private String randomName;

    //@Value("#{${animal.list.names}}") //для пропертиз
    @Value("${animals.list.names}")
    private List<String> randomNames;

    public AbstractAnimal(String breed, String name, Double cost) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        if (Objects.isNull(this.secretInfo)) {
            setSecretInfo();
        }
    }

    public AbstractAnimal(String breed, String name, Double cost, String character, String secretInfo, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.secretInfo = secretInfo;
        this.birthDate = birthDate;
        if (Objects.isNull(this.secretInfo)) {
            setSecretInfo();
        }
    }

    public AbstractAnimal() {
        if (Objects.isNull(this.secretInfo)) {
            setSecretInfo();
        }
        if (Objects.isNull(this.birthDate)) {
            this.birthDate = LocalDate.now();
        }
        if (Objects.isNull(this.cost)) {
            this.cost = 1000D;
        }
    }

    @JsonProperty("secretInfo")
    public String getSecretInfo() {
        return encryptString(secretInfo);
    }
    public void setSecretInfo() {
        Path secPath = Paths.get("src", "main", "resources", "secretStore", "secretInformation.txt");
        Random r = new Random();
        String line = "";
        List<String> list = new ArrayList<>();
        if (Files.exists(secPath)) {
            try {
                list = Files.readAllLines(secPath);
                line = list.get(r.nextInt(list.size()));

            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        this.secretInfo = line;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }

    @Override
    public LocalDate getBirthDate() throws InvalidAnimalBirtDateException {
        if (birthDate == null) {
            String[] message = {getBreed(), getName()};
            throw new InvalidAnimalBirtDateException(message);
        }
        return birthDate;
    }
    @JsonIgnore
    @Override
    public String getSecretInfomation() {
        return this.secretInfo;
    }
    @Override
    public String getType() {
        return this.breed;
    }
    @Override
    public String toString() {
        return breed + " " + name + " " +  cost + " " + birthDate;
    }

    public String toFileString() {
        return breed + " " + name + " " +  cost + " " + birthDate;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(breed);
        out.writeUTF(name);
        out.writeDouble(cost);
        out.writeUTF(String.valueOf(birthDate));
        out.writeUTF(encryptString(secretInfo));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.breed = in.readUTF();
        this.name = in.readUTF();
        this.cost = in.readDouble();
        this.birthDate = LocalDate.parse(in.readUTF());
        this.secretInfo = decryptString(in.readUTF());
    }

    private static String encryptString(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String decryptString(String data) {
        return new String(Base64.getDecoder().decode(data));
    }

    public void setRrandomName() {
        Random  r = new Random();
        int index = r.nextInt(randomNames.size());
        randomName = randomNames.get(index);
    }
}
