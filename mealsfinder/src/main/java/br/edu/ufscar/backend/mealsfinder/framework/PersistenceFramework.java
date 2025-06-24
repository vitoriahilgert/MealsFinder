package br.edu.ufscar.backend.mealsfinder.framework;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;
import org.springframework.web.client.HttpClientErrorException;
import org.yaml.snakeyaml.util.Tuple;

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
        Class<?> clazz = entity.getClass();

        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("A classe não é uma entidade JPA.");
        }

        UUID entityId = UUID.randomUUID();

        try (Connection conn = DriverManager.getConnection(databasePath)) {
            if (clazz.getSuperclass() != null && clazz.getSuperclass().isAnnotationPresent(Entity.class)) {
                // Inserir primeiro na superclass

                this.insertSQL(entity, clazz.getSuperclass(), conn, entityId);

                System.out.println("Inserido com sucesso na classe " + clazz.getSuperclass().getSimpleName());
            }

            this.insertSQL(entity, clazz, conn, entityId);

            System.out.println("Inserido com sucesso na classe " + clazz.getSuperclass().getSimpleName());
        }
    }

    private List<String> getColumnNames(Field[] fields) {
        List<String> columnNames = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);

                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name();
                columnNames.add(columnName);
            } else if (field.isAnnotationPresent(Embedded.class)) {
                field.setAccessible(true);

                Class<?> embeddedClass = field.getType();

                if (embeddedClass.isAnnotationPresent(Embeddable.class)) {
                    String embeddedName = embeddedClass.getAnnotation(Embeddable.class).name();
                    Field[] embeddedFields = embeddedClass.getDeclaredFields();
                    for (Field embeddedField : embeddedFields) {
                        if (embeddedField.isAnnotationPresent(Column.class)) {
                            Column columnAnnotation = embeddedField.getAnnotation(Column.class);
                            String columnName = embeddedName + "_" + columnAnnotation.name();
                            columnNames.add(columnName);
                        }
                    }
                }
            }
        }

        return columnNames;
    }

    private <T> List<Object> getValues(T entity, Field[] fields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                values.add(field.get(entity));
            } else if (field.isAnnotationPresent(Embedded.class)) {
                field.setAccessible(true);
                Object embeddedValue = field.get(entity);
                if (embeddedValue != null) {
                    for (Field embeddedField : embeddedValue.getClass().getDeclaredFields()) {
                        if (embeddedField.isAnnotationPresent(Column.class)) {
                            embeddedField.setAccessible(true);
                            values.add(embeddedField.get(embeddedValue));
                        }
                    }
                }
            }
        }
        return values;
    }

    private String buildInsertSQL(Class<?> clazz) {
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);

        String tableName = entityAnnotation.name();

        Field[] fields = clazz.getDeclaredFields();
        List<String> columnsList = this.getColumnNames(fields);
        String columns = String.join(", ", columnsList);

        boolean hasParentEntity = clazz.getSuperclass().isAnnotationPresent(Entity.class);

        String parentTableName = hasParentEntity ? clazz.getSuperclass().getSimpleName().toLowerCase() : null;

        StringBuilder sql = !hasParentEntity ? new StringBuilder("INSERT INTO " + tableName + "(" + columns + ")  VALUES (") :
                new StringBuilder("INSERT INTO " + tableName + "(" + parentTableName + "_id, " + columns + ")  VALUES (");

        int size = hasParentEntity ? columnsList.size() + 1 : columnsList.size();

        for (int i = 0; i < size; i++) {
            sql.append("?");
            if (i != size - 1) { sql.append(", "); }
        }

        sql.append(");");

        return sql.toString();
    }

    private <T> void insertSQL(T entity, Class<?> clazz, Connection connection, UUID entityId) throws Exception {

        String sql = this.buildInsertSQL(clazz);

        Field[] fields = clazz.getDeclaredFields();

        List<Object> values = this.getValues(entity, fields);

        if (fields[0].getAnnotation(Column.class).name().equals("id")) values.set(0, entityId);

        PreparedStatement statement = connection.prepareStatement(sql);
        int parameterIndex = 1;

        boolean hasParentEntity = clazz.getSuperclass().isAnnotationPresent(Entity.class);
        if (hasParentEntity) {
            statement.setObject(1, entityId);
            parameterIndex++;
        }
        for (Object value : values) {
            statement.setObject(parameterIndex++, value);
        }

        statement.executeUpdate();
    }

}
