package ru.mkustov.mtsspring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

import java.time.LocalDate;
import java.util.Random;

@Configuration
@ComponentScan("ru.mkustov.mtsspring")
@PropertySource("classpath:application.properties") //для пропертиз
//@ConfigurationProperties(prefix = "yaml")
//@PropertySource(value = "classpath:application.yml") // для ямла
//@ConfigurationProperties("") //взять из ямла 1:38
public class BeanConfiguration {

    @Bean("rndname")
    @Scope("prototype")
    public RandomName getRandomName() {
        return new RandomName();
    }
}
