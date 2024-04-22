package ru.mkustov.mtsspring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import ru.mkustov.mtsspring.AbstractAnimal;
import ru.mkustov.mtsspring.Dog;

@Configuration
@ComponentScan("ru.mkustov.mtsspring")
@PropertySource("classpath:application.properties")
//@PropertySource("classpath:application.yml")
//@ConfigurationProperties("") //взять из ямла 1:38
public class BeanConfiguration {
}
