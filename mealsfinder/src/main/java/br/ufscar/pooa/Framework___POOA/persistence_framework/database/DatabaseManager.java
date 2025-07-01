package br.ufscar.pooa.Framework___POOA.persistence_framework.database;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseManager {

    private final DataSource dataSource;

    public DatabaseManager(DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println("Nosso DatabaseManager customizado foi inicializado com o DataSource do Spring!");
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
