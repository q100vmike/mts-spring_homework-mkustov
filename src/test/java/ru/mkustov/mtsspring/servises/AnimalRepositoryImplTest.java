package ru.mkustov.mtsspring.servises;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.mkustov.mtsspring.config.RandomName;
import ru.mkustov.mtsspring.interfaces.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationTest.class)
@ActiveProfiles("test")
class AnimalRepositoryImplTest {

    @Autowired
    RandomName randomName;
    @Autowired
    AnimalRepositoryImpl repository;

    @Value("${animals.list.default}")
    int ymlDefaultValue;

    @Value("${animals.list.names}")
    List<String> ymlAnimalNames;

    @Test
    void isBeanRandomNameExist() {
        Map<String, List< Animal >> animals = repository.getAnimals();
        assertThat(animals.size()).isGreaterThan(0);
    }

    @Test
    void isDefaultValueYml10() {
        assertThat(ymlDefaultValue).isEqualTo(1000);
    }

    @Test
    void isListNamesYmlSize3() {
        List<String> expected = Arrays.asList("one", "two", "three", "four", "five", "six");
        Assert.assertEquals(expected, ymlAnimalNames);
    }

    @Test
    void isBeanRandomNameFromTestYml () {
        Map<String, List< Animal >> animals = repository.getAnimals();
        List<String> expected = Arrays.asList("one", "two", "three", "four", "five", "six");
        List<String> actual = animals.keySet().stream().toList();
        Assert.assertTrue(expected.containsAll(actual));
    }

}