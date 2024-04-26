package ru.mkustov.mtsspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mkustov.mtsspring.animals.AbstractAnimal;
import ru.mkustov.mtsspring.config.BeanConfiguration;
import ru.mkustov.mtsspring.config.RandomName;
import ru.mkustov.mtsspring.interfaces.Animal;
import ru.mkustov.mtsspring.servises.AnimalRepositoryImpl;
import ru.mkustov.mtsspring.servises.CreateAnimalServiceImpl;

import java.util.List;
import java.util.Map;
/*

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MtsspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtsspringApplication.class, args);


//для конфига в хмл
*/
/*		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml"
		);*//*


//для конфига в коде
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				BeanConfiguration.class);

		AbstractAnimal cat = context.getBean("someCat", AbstractAnimal.class);
		System.out.println(cat.getName());

		AbstractAnimal wolf = context.getBean("someWolf", AbstractAnimal.class);
		System.out.println(wolf.getName());

		CreateAnimalServiceImpl list = context.getBean(
				"animalList",CreateAnimalServiceImpl.class);
		System.out.println(list.val);

		for (String name : list.names) {
			System.out.println("name= " + name);
		}

		RandomName rname1 = context.getBean("rndname", RandomName.class);
		System.out.println(rname1.getRandomName());

		RandomName rname2 = context.getBean("rndname", RandomName.class);
		System.out.println(rname1.getRandomName());

		RandomName rname3 = context.getBean("rndname", RandomName.class);
		System.out.println(rname1.getRandomName());

		System.out.println("SPRING SPRING SPRING!!!!!!!!!!!!!");
		context.close();
	}
*/

	//public CommandLineRunner commandLineRunner(ApplicationContext ctx)
	@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
	public class MtsspringApplication implements CommandLineRunner {

		public static void main(String[] args) {
			SpringApplication.run(MtsspringApplication.class, args);
		}

		@Override
		public void run(String... args) throws Exception {

			var ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
			var wolf = ctx.getBean("someCat", AbstractAnimal.class);
			System.out.println(wolf.toString());

			System.out.println("--------------------");
			var repository = ctx.getBean("animalList", AnimalRepositoryImpl.class);

			Map<String, List<Animal>> animals = repository.getAnimals();

		}
}
