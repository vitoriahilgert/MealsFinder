package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.models.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.Client;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.util.UUID;

@SpringBootApplication
public class MealsFinderApplication {

	public static void main(String[] args) {
		testBDConnection(); // Teste da conexão com o BD

		SpringApplication.run(MealsFinderApplication.class, args);
	}

	static void testBDConnection() {
		System.out.println("--- Iniciando teste de banco de dados SQLite ---");

		String url = "jdbc:sqlite:mealsfinder.db";

		try (Connection conn = DriverManager.getConnection(url)) {

			System.out.println("✅ Conexão com o SQLite estabelecida com sucesso.");

			try (Statement stmt = conn.createStatement()) {

				String createTableSQL = CreateTableString();

				stmt.executeUpdate(createTableSQL);
				System.out.println("✅ Tabelas criadas com sucesso.");

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
			System.out.println("\n--- Iniciando testes do Persistence Framework ---");

			PersistenceFramework persistenceFramework = new PersistenceFramework();
			persistenceFramework.setDBAbsolutePath("jdbc:sqlite:mealsfinder.db");

			// Teste 1: Criar e inserir um Establishment (minimal data)
			System.out.println("\n🏪 Teste 1: Inserindo Establishment (dados mínimos)");
			Establishment establishment = new Establishment();
			establishment.setId(UUID.randomUUID());
			establishment.setUsername("manapoke");

			try {
				persistenceFramework.insert(establishment);
				System.out.println("✅ Establishment inserido com sucesso: " + establishment.getUsername());
			} catch (Exception e) {
				System.err.println("❌ Erro ao inserir establishment: " + e.getMessage());
				e.printStackTrace();
			}

			// Teste 2: Criar e inserir um Client (minimal data)
			System.out.println("\n👤 Teste 2: Inserindo Client (dados mínimos)");
			Client client = new Client();
			client.setId(UUID.randomUUID());
			client.setUsername("joaosilva");

			try {
				persistenceFramework.insert(client);
				System.out.println("✅ Client inserido com sucesso: " + client.getUsername());
			} catch (Exception e) {
				System.err.println("❌ Erro ao inserir client: " + e.getMessage());
				e.printStackTrace();
			}

			// Teste 3: Criar Establishment com dados completos
			System.out.println("\n🍕 Teste 3: Inserindo Establishment (dados completos)");
			Establishment pizzaria = new Establishment();
			pizzaria.setId(UUID.randomUUID());
			pizzaria.setCnpj("12.345.678/0001-90");
			pizzaria.setDelivery(true);
			pizzaria.setEstablishmentType(EstablishmentTypesEnum.Pizzaria);
			pizzaria.setStatus(StatusEnum.APPROVED);
			pizzaria.setRejections(0);
			pizzaria.setInPerson(true);
			pizzaria.setEmail("bellapizza@example.com");
			pizzaria.setPassword("pizza123");
			pizzaria.setPhoneNumber("(16) 3333-4444");
			pizzaria.setUsername("bellapizza");
			pizzaria.setBio("Pizzas artesanais desde 1985");
			pizzaria.setStreet("Av. São Carlos");
			pizzaria.setNumber("567");
			pizzaria.setNeighborhood("Vila Prado");
			pizzaria.setCity("São Carlos");
			pizzaria.setState("SP");
			pizzaria.setCep("13566-590");
			pizzaria.setCountry("Brasil");

			try {
				persistenceFramework.insert(pizzaria);
				System.out.println("✅ Pizzaria inserida com sucesso: " + pizzaria.getUsername());
			} catch (Exception e) {
				System.err.println("❌ Erro ao inserir pizzaria: " + e.getMessage());
				e.printStackTrace();
			}

			// Teste 4: Criar Client com dados completos e preferências
			System.out.println("\n👩 Teste 4: Inserindo Client (dados completos)");
			Client maria = new Client();
			maria.setId(UUID.randomUUID());
			maria.setEmail("maria@example.com");
			maria.setUsername("mariafoods");
			maria.setPassword("maria456");
			maria.setPhoneNumber("(16) 88888-5678");
			maria.setBio("Crítica gastronômica amadora");

			// Adicionar preferências
			maria.addFoodLike(FoodTypesEnum.VEGANA);
			maria.addFoodLike(FoodTypesEnum.VEGETARIANA);
			maria.addFoodLike(FoodTypesEnum.SAUDAVEL);
			maria.addFoodDislike(FoodTypesEnum.FAST_FOOD);

			maria.prepareForDatabase();

			try {
				persistenceFramework.insert(maria);
				System.out.println("✅ Client Maria inserida com sucesso: " + maria.getUsername());
			} catch (Exception e) {
				System.err.println("❌ Erro ao inserir client Maria: " + e.getMessage());
				e.printStackTrace();
			}

			System.out.println("\n--- Testes do Persistence Framework finalizados ---");
		};
	}

	private static String CreateTableString() {
		return "-- Create tables for MealsFinder application - SQLite version\n" +
				"-- Creating separate tables to match @Entity annotations\n" +
				"-- SQLite doesn't have UUID type, using TEXT for IDs\n" +
				"-- All fields allow NULL except essential ones, with sensible defaults\n" +
				"-- NO users table - only clients and establishments with inherited User fields\n" +
				"\n" +
				"-- Clients table (matches @Entity(tableName = \"clients\"))\n" +
				"-- Contains all User fields + Client-specific fields\n" +
				"CREATE TABLE IF NOT EXISTS clients (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    \n" +
				"    -- User inherited fields\n" +
				"    email TEXT UNIQUE,\n" +
				"    phone_number TEXT,\n" +
				"    username TEXT UNIQUE,\n" +
				"    password TEXT,\n" +
				"    profile_pic_url TEXT,\n" +
				"    is_account_confirmed INTEGER DEFAULT 0,\n" +
				"    confirmation_code TEXT,\n" +
				"    bio TEXT,\n" +
				"    creation_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    last_modified_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    \n" +
				"    -- Client-specific fields\n" +
				"    food_likes TEXT DEFAULT '',\n" +
				"    food_dislikes TEXT DEFAULT '',\n" +
				"    \n" +
				"    -- User @Collection fields stored as text\n" +
				"    created_content_ids TEXT DEFAULT '',\n" +
				"    liked_content_ids TEXT DEFAULT '',\n" +
				"    saved_content_ids TEXT DEFAULT '',\n" +
				"    following_ids TEXT DEFAULT '',\n" +
				"    followers_ids TEXT DEFAULT '',\n" +
				"    blocked_user_ids TEXT DEFAULT '',\n" +
				"    blocked_by_user_ids TEXT DEFAULT ''\n" +
				");\n" +
				"\n" +
				"-- Establishments table (matches @Entity(tableName = \"establishments\"))\n" +
				"-- Contains all User fields + Establishment-specific fields\n" +
				"CREATE TABLE IF NOT EXISTS establishments (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    \n" +
				"    -- User inherited fields\n" +
				"    email TEXT UNIQUE,\n" +
				"    phone_number TEXT,\n" +
				"    username TEXT UNIQUE,\n" +
				"    password TEXT,\n" +
				"    profile_pic_url TEXT,\n" +
				"    is_account_confirmed INTEGER DEFAULT 0,\n" +
				"    confirmation_code TEXT,\n" +
				"    bio TEXT,\n" +
				"    creation_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    last_modified_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    \n" +
				"    -- Establishment-specific fields  \n" +
				"    cnpj TEXT,\n" +
				"    establishment_type TEXT,\n" +
				"    is_delivery INTEGER DEFAULT 0,\n" +
				"    is_in_person INTEGER DEFAULT 1,\n" +
				"    status TEXT DEFAULT 'PENDING',\n" +
				"    street TEXT,\n" +
				"    number TEXT,\n" +
				"    complement TEXT,\n" +
				"    neighborhood TEXT,\n" +
				"    city TEXT,\n" +
				"    state TEXT,\n" +
				"    cep TEXT,\n" +
				"    country TEXT,\n" +
				"    rejections INTEGER DEFAULT 0,\n" +
				"    \n" +
				"    -- User @Collection fields stored as text\n" +
				"    created_content_ids TEXT DEFAULT '',\n" +
				"    liked_content_ids TEXT DEFAULT '',\n" +
				"    saved_content_ids TEXT DEFAULT '',\n" +
				"    following_ids TEXT DEFAULT '',\n" +
				"    followers_ids TEXT DEFAULT '',\n" +
				"    blocked_user_ids TEXT DEFAULT '',\n" +
				"    blocked_by_user_ids TEXT DEFAULT '',\n" +
				"    \n" +
				"    -- Establishment @Collection fields stored as text\n" +
				"    image_ids TEXT DEFAULT '',\n" +
				"    received_review_ids TEXT DEFAULT ''\n" +
				");\n" +
				"\n" +
				"-- Posts table (matches @Entity(tableName = \"posts\"))\n" +
				"-- Contains all Content fields + Post-specific fields\n" +
				"CREATE TABLE IF NOT EXISTS posts (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    \n" +
				"    -- Content inherited fields\n" +
				"    text TEXT,\n" +
				"    creator_id TEXT,\n" +
				"    creation_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    last_modified_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    \n" +
				"    -- Post-specific fields\n" +
				"    description TEXT,\n" +
				"    \n" +
				"    -- Content @Collection fields stored as text\n" +
				"    liked_by_ids TEXT DEFAULT '',\n" +
				"    \n" +
				"    -- Post @Collection fields stored as text\n" +
				"    comments_ids TEXT DEFAULT '',\n" +
				"    reviews_ids TEXT DEFAULT ''\n" +
				");\n" +
				"\n" +
				"-- Comments table (matches @Entity(tableName = \"comments\"))\n" +
				"-- Contains all Content fields + Comment-specific fields\n" +
				"CREATE TABLE IF NOT EXISTS comments (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    \n" +
				"    -- Content inherited fields\n" +
				"    text TEXT,\n" +
				"    creator_id TEXT,\n" +
				"    creation_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    last_modified_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    \n" +
				"    -- Comment-specific fields\n" +
				"    post_id TEXT,\n" +
				"    \n" +
				"    -- Content @Collection fields stored as text\n" +
				"    liked_by_ids TEXT DEFAULT ''\n" +
				");\n" +
				"\n" +
				"-- Reviews table (matches @Entity(tableName = \"reviews\"))\n" +
				"-- Contains all Content + Post fields + Review-specific fields\n" +
				"CREATE TABLE IF NOT EXISTS reviews (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    \n" +
				"    -- Content inherited fields\n" +
				"    text TEXT,\n" +
				"    creator_id TEXT,\n" +
				"    creation_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    last_modified_date TEXT DEFAULT CURRENT_TIMESTAMP,\n" +
				"    \n" +
				"    -- Post inherited fields (Review extends Post)\n" +
				"    description TEXT,\n" +
				"    \n" +
				"    -- Review-specific fields\n" +
				"    price REAL,\n" +
				"    rating REAL,\n" +
				"    is_delivery INTEGER,\n" +
				"    establishment_rating REAL,\n" +
				"    service_rating REAL,\n" +
				"    delivery_rating REAL,\n" +
				"    reviewed_post_id TEXT,\n" +
				"    \n" +
				"    -- Content @Collection fields stored as text\n" +
				"    liked_by_ids TEXT DEFAULT '',\n" +
				"    \n" +
				"    -- Post @Collection fields stored as text\n" +
				"    comments_ids TEXT DEFAULT '',\n" +
				"    reviews_ids TEXT DEFAULT ''\n" +
				");\n" +
				"\n" +
				"-- Images table\n" +
				"CREATE TABLE IF NOT EXISTS images (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    url TEXT,\n" +
				"    type TEXT,\n" +
				"    establishment_id TEXT\n" +
				");\n" +
				"\n" +
				"-- User Activity table\n" +
				"CREATE TABLE IF NOT EXISTS user_activity (\n" +
				"    id TEXT PRIMARY KEY,\n" +
				"    user_id TEXT,\n" +
				"    entity_id TEXT,\n" +
				"    timestamp INTEGER,\n" +
				"    engagement_score REAL\n" +
				");\n" +
				"\n" +
				"-- Create indexes for better performance (optional for example project)\n" +
				"CREATE INDEX IF NOT EXISTS idx_clients_email ON clients(email);\n" +
				"CREATE INDEX IF NOT EXISTS idx_clients_username ON clients(username);\n" +
				"CREATE INDEX IF NOT EXISTS idx_establishments_email ON establishments(email);\n" +
				"CREATE INDEX IF NOT EXISTS idx_establishments_username ON establishments(username);\n" +
				"CREATE INDEX IF NOT EXISTS idx_establishments_status ON establishments(status);\n" +
				"CREATE INDEX IF NOT EXISTS idx_content_creator ON content(creator_id);\n" +
				"CREATE INDEX IF NOT EXISTS idx_posts_creator ON posts(creator_id);\n" +
				"CREATE INDEX IF NOT EXISTS idx_comments_post ON comments(post_id);\n" +
				"CREATE INDEX IF NOT EXISTS idx_comments_creator ON comments(creator_id);\n" +
				"CREATE INDEX IF NOT EXISTS idx_reviews_creator ON reviews(creator_id);\n" +
				"CREATE INDEX IF NOT EXISTS idx_images_establishment ON images(establishment_id);\n" +
				"CREATE INDEX IF NOT EXISTS idx_user_activity_user ON user_activity(user_id);";
	}
}