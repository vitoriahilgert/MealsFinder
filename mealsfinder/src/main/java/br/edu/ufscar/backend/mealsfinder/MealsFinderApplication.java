package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.models.Address;
import br.edu.ufscar.backend.mealsfinder.models.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.UserRoleEnum;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication
public class MealsFinderApplication {

	public static void main(String[] args) {
		 testBDConnection(); // Descomente essa linha se quiser testar a conexão com o BD

		SpringApplication.run(MealsFinderApplication.class, args);
	}

	static void testBDConnection() {
		System.out.println("--- Iniciando teste de banco de dados SQLite ---");

		String url = "jdbc:sqlite:mealsfinder.db";

		try (Connection conn = DriverManager.getConnection(url)) {

			System.out.println("Conexão com o SQLite estabelecida com sucesso.");

			try (Statement stmt = conn.createStatement()) {

				String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
						"id UUID PRIMARY KEY, " +
						"email VARCHAR(255) UNIQUE NOT NULL, " +
						"phone_number VARCHAR(30) UNIQUE NOT NULL, " +
						"username VARCHAR(100) UNIQUE NOT NULL, " +
						"password VARCHAR(255) NOT NULL, " +
						"profile_pic_url TEXT, " +
						"is_account_confirmed BOOLEAN NOT NULL DEFAULT FALSE, " +
						"confirmation_code VARCHAR(100), " +
						"bio TEXT, " +
						"role VARCHAR(31) NOT NULL);";

				stmt.executeUpdate(createUserTableSQL);
				System.out.println("Tabela users criada ou já existente.");

				String createEstablishmentTableSQL = "CREATE TABLE IF NOT EXISTS establishments (" +
						"user_id UUID PRIMARY KEY, " +
						"cnpj VARCHAR(14) NOT NULL UNIQUE, " +
						"type SMALLINT NOT NULL, " +
						"is_delivery BOOLEAN NOT NULL, " +
						"is_in_person BOOLEAN NOT NULL, " +
						"status SMALLINT NOT NULL, " +
						"rejections SMALLINT NOT NULL DEFAULT 0, " +
						"address_country VARCHAR(255), " +
						"address_street VARCHAR(255), " +
						"address_number VARCHAR(20), " +
						"address_complement VARCHAR(100), " +
						"address_neighborhood VARCHAR(100), " +
						"address_city VARCHAR(100), " +
						"address_state VARCHAR(2), " +
						"address_cep VARCHAR(10), " +
						"CONSTRAINT fk_establishment_user " +
						"FOREIGN KEY(user_id) " +
						"REFERENCES users(id) " +
						"ON UPDATE CASCADE ON DELETE CASCADE); ";

				stmt.executeUpdate(createEstablishmentTableSQL);
				System.out.println("Tabela establishments criada ou já existente.");
			}

		} catch (SQLException e) {
			System.err.println("Ocorreu um erro ao conectar ou operar o banco de dados:");
			e.printStackTrace();
		}

		System.out.println("\n--- Teste de banco de dados finalizado ---");
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
			establishment.setRejections((short) 0);
			establishment.setInPerson(true);
			establishment.setEmail("manapoke@gmail.com");
			establishment.setPassword("password");
			establishment.setPhoneNumber("(16) 98183-5500");
			establishment.setUsername("manapoke");
			establishment.setRole(UserRoleEnum.ESTABLISHMENT);

			Address address = new Address();
			address.setCep("13574-290");
			address.setCity("São Carlos");
			address.setState("SP");
			address.setNeighborhood("Vila Prado");
			address.setCountry("Brasil");
			address.setNumber("568");
			address.setStreet("Rua Antonio de Almeida Leite");

			establishment.setAddress(address);

			persistenceFramework.insert(establishment);

		};
	}

}
