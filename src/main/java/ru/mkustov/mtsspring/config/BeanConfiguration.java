package ru.mkustov.mtsspring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mkustov.mtsspring.AbstractAnimal;
import ru.mkustov.mtsspring.Dog;

@Configuration
@ConfigurationProperties("") //взять из ямла 1:38
public class BeanConfiguration {
    @Bean
    @Scope("prototype")
    public AbstractAnimal abstractAnimal(){
        return new Dog();
    }
}
