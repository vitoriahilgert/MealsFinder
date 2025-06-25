package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.models.Address;
import br.edu.ufscar.backend.mealsfinder.models.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.Post;
import br.edu.ufscar.backend.mealsfinder.models.Product;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.UserRoleEnum;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
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

			String createPostTableSQL = "CREATE TABLE IF NOT EXISTS posts" +
					"(" +
					"    id          TEXT PRIMARY KEY, " +
					"    user_id     TEXT NOT NULL, " +
					"    description TEXT, " +
					"    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP, " +
					"    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE" +
					");";

			stmt.executeUpdate(createPostTableSQL);
			System.out.println("Tabela posts criada ou já existente");


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

				String createPostTableSQL = "CREATE TABLE IF NOT EXISTS posts" +
						"(" +
						"    id          TEXT PRIMARY KEY, " +
						"    user_id     TEXT NOT NULL, " +
						"    description TEXT, " +
						"    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP, " +
						"    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE" +
						");";

				stmt.executeUpdate(createPostTableSQL);
				System.out.println("Tabela posts criada ou já existente");
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

			// --- Teste 3: Exists
			System.out.println("\n--- TESTE 3: Teste Exists ---");
			testExists(framework);

			// --- Teste 4: Find all---
			System.out.println("\n--- TESTE 4: Testando findAll para Establishment ---");
			testEstablishmentAndFindAll(framework);

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
			UUID generatedId = framework.save(establishment);
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

	private void testExists(PersistenceFramework framework) throws Exception {
		Post post = new Post();
		post.setDescription("Post de teste");
		post.setUserId(UUID.fromString("92fa7bbd-e349-4b8b-8f76-2359ef1435c8"));
		post.setCreatedAt(LocalDateTime.now());


		post.setId(UUID.fromString("4e3f5344-fbf2-43cf-b9fc-73b5c294c4e2"));

		boolean postExists = framework.existsById(post);
		System.out.println("Post existe: " + postExists);
	}

	private void testEstablishmentAndFindAll(PersistenceFramework framework) {
		System.out.println("\nPASSO 1: Inserindo múltiplos estabelecimentos para o teste...");

		Establishment establishment1 = new Establishment();
		establishment1.setCnpj("79.516.761/1001-12");
		establishment1.setDelivery(true);
		establishment1.setType(EstablishmentTypesEnum.A);
		establishment1.setStatus(StatusEnum.OPEN);
		establishment1.setRejections((short) 0);
		establishment1.setInPerson(true);
		establishment1.setEmail("becodatorta@gmail.com");
		establishment1.setPassword("password123");
		establishment1.setPhoneNumber("(16) 99999-1111");
		establishment1.setUsername("becodatorta");
		establishment1.setRole(UserRoleEnum.ESTABLISHMENT);

		Address address1 = new Address();
		address1.setCountry("Brasil");
		address1.setStreet("Rua A");
		address1.setNumber("100");
		address1.setNeighborhood("Bairro A");
		address1.setCity("Cidade A");
		address1.setState("SP");
		address1.setCep("11111-111");
		establishment1.setAddress(address1);

		Establishment establishment2 = new Establishment();
		establishment2.setCnpj("12.345.478/0001-99");
		establishment2.setDelivery(false);
		establishment2.setType(EstablishmentTypesEnum.B);
		establishment2.setStatus(StatusEnum.CLOSED);
		establishment2.setRejections((short) 1);
		establishment2.setInPerson(true);
		establishment2.setEmail("pizzariadoze@example.com");
		establishment2.setPassword("senha456");
		establishment2.setPhoneNumber("(16) 98888-2222");
		establishment2.setUsername("pizzadoze");
		establishment2.setRole(UserRoleEnum.ESTABLISHMENT);

		Address address2 = new Address();
		address2.setCountry("Brasil");
		address2.setStreet("Rua B");
		address2.setNumber("200");
		address2.setNeighborhood("Bairro B");
		address2.setCity("Cidade B");
		address2.setState("RJ");
		address2.setCep("22222-222");
		establishment2.setAddress(address2);

		try {
			framework.save(establishment1);
			framework.save(establishment2);
			System.out.println("Dois estabelecimentos inseridos com sucesso.");

			System.out.println("\nPASSO 2: Testando findAll para a tabela 'establishments'...");
			List<Establishment> allEstablishments = framework.findAll(Establishment.class);

			if (allEstablishments != null && !allEstablishments.isEmpty()) {
				System.out.println(">> SUCESSO: findAll encontrou " + allEstablishments.size() + " estabelecimentos.");
				for (Establishment est : allEstablishments) {
					System.out.println("   - Username: " + est.getUsername() + ", CNPJ: " + est.getCnpj() + ", ID: " + est.getId());
				}
			} else {
				System.err.println(">> FALHA: findAll não retornou nenhum estabelecimento.");
			}

		} catch (Exception e) {
			System.err.println("Ocorreu um erro durante o teste de Establishment:");
			e.printStackTrace();
		}
	}
}
