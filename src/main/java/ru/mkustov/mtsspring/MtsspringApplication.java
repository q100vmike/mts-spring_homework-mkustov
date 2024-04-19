package ru.mkustov.mtsspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MtsspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtsspringApplication.class, args);
		System.out.println("SPRING SPRING SPRING!!!!!!!!!!!!!");
	}

	//public CommandLineRunner commandLineRunner(ApplicationContext ctx)

}
