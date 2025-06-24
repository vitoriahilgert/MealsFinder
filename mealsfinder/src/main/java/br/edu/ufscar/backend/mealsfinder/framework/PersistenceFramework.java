package br.edu.ufscar.backend.mealsfinder.framework;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class PersistenceFramework {
    private String databasePath;

    public void setDBAbsolutePath(String dbAbsolutePath) {
        this.databasePath = dbAbsolutePath;
    }

    public <T> void insert(T entity) throws Exception {
        Connection conn = DriverManager.getConnection(databasePath);

        Class<?> clazz = entity.getClass();

        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("A classe não é uma entidade JPA.");
        }

        if (clazz.getSuperclass() != null && clazz.getSuperclass().isAnnotationPresent(Entity.class)) {
            // Inserir primeiro na superclass
            String sql = this.buildInsertSQL(clazz.getSuperclass());

            this.insertSQL(entity.getClass().getSuperclass(), sql, conn);

            System.out.println("Inserido com sucesso na classe " + clazz.getSuperclass().getSimpleName());
        }

        String sql = this.buildInsertSQL(clazz);

        this.insertSQL(entity.getClass(), sql, conn);

        System.out.println("Inserido com sucesso na classe " + clazz.getSuperclass().getSimpleName());

    }

    private List<String> getColumnNames(Field[] fields) {
        List<String> columnNames = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName;
                if (columnAnnotation.name() == null || columnAnnotation.name().isEmpty()) {
                    columnName = field.getName().toLowerCase();
                } else {
                    String regex = "(.)(?=[A-Z])";
                    String replacement = "$1_";

                    columnName = columnAnnotation.name().replaceAll(regex, replacement).toLowerCase();
                }
                columnNames.add(columnName);
            }
        }

        return columnNames;
    }

    private String buildInsertSQL(Class<?> clazz) {
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);

        String tableName;

        if (entityAnnotation.name() == null || entityAnnotation.name().isEmpty()) {
            tableName = clazz.getSimpleName().toLowerCase();
        } else {
            String regex = "(.)(?=[A-Z])";
            String replacement = "$1_";

            tableName = entityAnnotation.name().replaceAll(regex, replacement).toLowerCase();
        }

        Field[] fields = clazz.getDeclaredFields();
        List<String> columnsList = this.getColumnNames(fields);
        String columns = String.join(", ", columnsList);

        StringBuilder sql = (clazz.getSuperclass() == null) ? new StringBuilder("INSERT INTO " + tableName + " (id, " + columns + ")  VALUES (") :
                new StringBuilder("INSERT INTO " + tableName + " (, " + columns + ")  VALUES (");

        int size = (clazz.getSuperclass() == null) ? columnsList.size() + 1 : columnsList.size();

        for (int i = 0; i < size; i++) {
            sql.append("?");
            if (i != size - 1) { sql.append(", "); }
        }

        sql.append(");");

        return sql.toString();
    }

    private <T> void insertSQL(T entity, String sql, Connection connection) throws Exception {
        Class<?> clazz = entity.getClass();

        Field[] fields = clazz.getSuperclass().getDeclaredFields();
        List<String> columnsList = this.getColumnNames(fields);

        PreparedStatement statement = connection.prepareStatement(sql);

        int i = 0; int size = columnsList.size();

        if (clazz.getSuperclass() == null) {
            statement.setString(1, UUID.randomUUID().toString());
            i = 1; size +=1;
        }

        for (; i < size; i++) {
            Object value = fields[i].get(entity);
            statement.setObject(i + 1, value);
        }

        statement.executeUpdate();
    }

}
