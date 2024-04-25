package ru.mkustov.mtsspring.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("ru.mkustov.mtsspring")
@PropertySource("classpath:application.properties")
//@PropertySource("classpath:application.yml")
//@ConfigurationProperties("") //взять из ямла 1:38
public class BeanConfiguration {
}
