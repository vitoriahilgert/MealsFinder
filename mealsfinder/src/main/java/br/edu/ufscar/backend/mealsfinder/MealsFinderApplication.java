package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.models.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MealsFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealsFinderApplication.class, args);
	}

	@Bean
	public CommandLineRunner persistenceFrameworkTest() {
		return (args) -> {
			PersistenceFramework persistenceFramework = new PersistenceFramework();

			persistenceFramework.setDBAbsolutePath("jdbc:sqlite:mealsfinder.db");

			Establishment establishment = new Establishment();
			establishment.setCnpj("79.536.761/0001-12");
			establishment.setDelivery(true);
			establishment.setType(EstablishmentTypesEnum.A);
			establishment.setStatus(StatusEnum.OPEN);
			establishment.setRejections(0);
			establishment.setInPerson(true);
			establishment.setEmail("manapoke@gmail.com");
			establishment.setPassword("password");
			establishment.setPhoneNumber("(16) 98183-5500");
			establishment.setUsername("manapoke");

			persistenceFramework.insert(establishment);

		};
	}

}
