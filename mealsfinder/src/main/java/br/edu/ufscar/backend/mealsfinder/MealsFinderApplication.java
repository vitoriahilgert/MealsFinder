package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.models.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication
public class MealsFinderApplication {

	public static void main(String[] args) {
//		 testBDConnection(); // Descomente essa linha se quiser testar a conexão com o BD

		SpringApplication.run(MealsFinderApplication.class, args);
	}

	static void testBDConnection() {
		System.out.println("--- Iniciando teste de banco de dados SQLite ---");

		String url = "jdbc:sqlite:mealsfinder.db";

		try (Connection conn = DriverManager.getConnection(url)) {

			System.out.println("✅ Conexão com o SQLite estabelecida com sucesso.");

			try (Statement stmt = conn.createStatement()) {

				String createTableSQL = "CREATE TABLE IF NOT EXISTS Usuario (" +
						"id INTEGER PRIMARY KEY, " +
						"nome TEXT NOT NULL, " +
						"email TEXT NOT NULL UNIQUE);";

				stmt.executeUpdate(createTableSQL);
				System.out.println("✅ Tabela 'Usuario' criada ou já existente.");

				String insertSQL = "INSERT INTO Usuario(nome, email) VALUES(?, ?)";

				try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
					pstmt.setString(1, "Ana Carolina");
					pstmt.setString(2, "ana.carolina@email.com");
					try { pstmt.executeUpdate(); } catch (SQLException ignored) { }


					pstmt.setString(1, "Bruno Martins");
					pstmt.setString(2, "bruno.m@email.com");
					try { pstmt.executeUpdate(); } catch (SQLException ignored) {  }

					System.out.println("✅ Dados de exemplo inseridos.");
				}

				System.out.println("\n--- Buscando todos os usuários no banco: ---");
				String selectSQL = "SELECT id, nome, email FROM Usuario";

				try (ResultSet rs = stmt.executeQuery(selectSQL)) {
					while (rs.next()) {
						System.out.printf("ID: %d, Nome: %s, Email: %s\n",
								rs.getInt("id"),
								rs.getString("nome"),
								rs.getString("email"));
					}
				}
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
			establishment.setEstablishmentType(EstablishmentTypesEnum.Lanchonete);
			establishment.setStatus(StatusEnum.PENDING);
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
