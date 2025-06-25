package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.models.Address;
import br.edu.ufscar.backend.mealsfinder.models.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.Product;
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
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class MealsFinderApplication {

	public static void main(String[] args) {
//		 testBDConnection(); // Descomente essa linha se quiser testar a conexão com o BD
		prepareDatabaseForTest();
		SpringApplication.run(MealsFinderApplication.class, args);
	}

	static void prepareDatabaseForTest() {
		System.out.println("--- Preparando banco de dados para o teste ---");
		String url = "jdbc:sqlite:mealsfinder.db";

		try (Connection conn = DriverManager.getConnection(url);
			 Statement stmt = conn.createStatement()) {

			System.out.println("Conexão com o SQLite estabelecida.");

			// Apaga as tabelas antigas para garantir um teste limpo
			stmt.executeUpdate("DROP TABLE IF EXISTS products;");
			stmt.executeUpdate("DROP TABLE IF EXISTS establishments;");
			stmt.executeUpdate("DROP TABLE IF EXISTS users;");
			System.out.println("Tabelas antigas (se existiam) removidas.");

			// Cria a tabela 'users'
			String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
					"id TEXT PRIMARY KEY, " +
					"email VARCHAR(255) UNIQUE NOT NULL, " +
					"phone_number VARCHAR(30) UNIQUE NOT NULL, " +
					"username VARCHAR(100) UNIQUE NOT NULL, " +
					"password VARCHAR(255) NOT NULL, " +
					"profile_pic_url TEXT, " +
					"is_account_confirmed BOOLEAN NOT NULL DEFAULT FALSE, " +
					"confirmation_code VARCHAR(100), " +
					"bio TEXT, " +
					"role INTEGER NOT NULL);";
			stmt.executeUpdate(createUserTableSQL);
			System.out.println("Tabela 'users' criada.");

			// Cria a tabela 'establishments'
			String createEstablishmentTableSQL = "CREATE TABLE IF NOT EXISTS establishments (" +
					"user_id TEXT PRIMARY KEY, " +
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
					"FOREIGN KEY(user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE);";
			stmt.executeUpdate(createEstablishmentTableSQL);
			System.out.println("Tabela 'establishments' criada.");

			// Cria a tabela 'products'
			String createProductTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
					"product_id INTEGER PRIMARY KEY, " + // INTEGER para Long no SQLite
					"name VARCHAR(255) NOT NULL, " +
					"price REAL NOT NULL);"; // REAL para Double no SQLite
			stmt.executeUpdate(createProductTableSQL);
			System.out.println("Tabela 'products' criada.");


		} catch (SQLException e) {
			System.err.println("Ocorreu um erro ao preparar o banco de dados:");
			e.printStackTrace();
		}
		System.out.println("\n--- Banco de dados pronto para o teste ---");
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
			System.out.println("\n--- INICIANDO TESTE DO FRAMEWORK DE PERSISTÊNCIA ---");
			PersistenceFramework framework = new PersistenceFramework();
			framework.setDBAbsolutePath("jdbc:sqlite:mealsfinder.db");

			// --- Teste 1: Establishment (UUID PK) ---
			System.out.println("\n--- TESTE 1: Entidade com chave UUID ---");
			testEstablishment(framework);

			// --- Teste 2: Product (Long PK) ---
			System.out.println("\n--- TESTE 2: Entidade com chave Long ---");
			testProduct(framework);

			System.out.println("\n--- TESTE DO FRAMEWORK FINALIZADO ---");
		};
	}

	private void testEstablishment(PersistenceFramework framework) {
		Establishment establishment = new Establishment();
		establishment.setCnpj("79.536.761/1001-12");
		establishment.setDelivery(true);
		establishment.setType(EstablishmentTypesEnum.A);
		establishment.setStatus(StatusEnum.OPEN);
		establishment.setRejections((short) 0);
		establishment.setInPerson(true);
		establishment.setEmail("manapokea@agmail.com");
		establishment.setPassword("password");
		establishment.setPhoneNumber("(16) 98183-5500");
		establishment.setUsername("manaapokea");
		establishment.setRole(UserRoleEnum.ESTABLISHMENT);

		Address address = new Address();
		address.setCep("13544-240");
		address.setCity("São Carlos");
		address.setState("SP");
		address.setNeighborhood("Vila Prado");
		address.setCountry("Brasil");
		address.setNumber("568");
		address.setStreet("Rua Antonio de Almeida Leite");
		establishment.setAddress(address);

		try {
			UUID generatedId = framework.insert(establishment);
			System.out.println("PASSO 1: Objeto 'Establishment' inserido com ID: " + generatedId);
			System.out.println("PASSO 2: Buscando objeto com o ID gerado...");
			Optional<Establishment> foundOpt = framework.findByPrimaryKey(Establishment.class, generatedId);
			if (foundOpt.isPresent()) {
				System.out.println(">> SUCESSO: 'Establishment' encontrado! (ID: " + foundOpt.get().getId() + ")");
			} else {
				System.err.println(">> FALHA: 'Establishment' não encontrado.");
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro durante o teste de Establishment:");
			e.printStackTrace();
		}
	}

	private void testProduct(PersistenceFramework framework) {
		Product product = new Product();
		Long productId = 1L;
		product.setProductId(productId);
		product.setName("Refrigerante");
		product.setPrice(5.50);

		String sql = "INSERT INTO products (product_id, name, price) VALUES (?, ?, ?)";
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mealsfinder.db");
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, product.getProductId());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			stmt.executeUpdate();

			System.out.println("PASSO 1: Objeto 'Product' inserido com ID: " + productId);
			System.out.println("PASSO 2: Buscando objeto com o ID gerado...");
			Optional<Product> foundOpt = framework.findByPrimaryKey(Product.class, productId);

			if (foundOpt.isPresent()) {
				Product found = foundOpt.get();
				System.out.println(">> SUCESSO: 'Product' encontrado!");
				System.out.println("   - ID: " + found.getProductId());
				System.out.println("   - Nome: " + found.getName());
				System.out.println("   - Preço: " + found.getPrice());
			} else {
				System.err.println(">> FALHA: 'Product' não encontrado.");
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro durante o teste de Product:");
			e.printStackTrace();
		}
	}
}
