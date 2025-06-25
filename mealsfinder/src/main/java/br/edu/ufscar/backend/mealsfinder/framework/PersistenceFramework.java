package br.edu.ufscar.backend.mealsfinder.framework;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PersistenceFramework {
    private String databasePath;

    public void setDBAbsolutePath(String dbAbsolutePath) {
        this.databasePath = dbAbsolutePath;
    }

    public <T> boolean existsById(T entity) throws Exception {
        Class<?> clazz = entity.getClass();

        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("A classe não é uma entidade JPA.");
        }

        String entityName = clazz.getAnnotation(Entity.class).name();

        Field primaryKeyField = this.getPrimaryKeyField(clazz);
        primaryKeyField.setAccessible(true);

        String primaryKeyName = primaryKeyField.getName();

        String sql = "SELECT * FROM " + entityName + " WHERE " + primaryKeyName + " = ?;";

        try (Connection connection = DriverManager.getConnection(databasePath)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            Object value = primaryKeyField.get(entity);
            statement.setObject(1, value);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <T> void save(T entity) throws Exception {
        Class<?> clazz = entity.getClass();

        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("A classe não é uma entidade JPA.");
        }

        UUID entityId = UUID.randomUUID();

        try (Connection conn = DriverManager.getConnection(databasePath)) {
            this.insert(entity, clazz, conn, entityId);
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

    private <T> void insert(T entity, Class<?> clazz, Connection connection, UUID entityId) throws Exception {
        boolean hasParentEntity = clazz.getSuperclass().isAnnotationPresent(Entity.class);

        if (hasParentEntity) {
            insert(entity, clazz.getSuperclass(), connection, entityId);
        }

        String sql = this.buildInsertSQL(clazz);

        Field[] fields = clazz.getDeclaredFields();

        List<Object> values = this.getValues(entity, fields);

        if (fields[0].getAnnotation(Column.class).name().equals("id")) values.set(0, entityId);

        PreparedStatement statement = connection.prepareStatement(sql);
        int parameterIndex = 1;

        if (hasParentEntity) {
            statement.setObject(1, entityId);
            parameterIndex++;
        }
        for (Object value : values) {
            statement.setObject(parameterIndex++, value);
        }

        statement.executeUpdate();

        System.out.println("Inserido com sucesso na tabela " + clazz.getAnnotation(Entity.class).name());
    }

    private Field getPrimaryKeyField(Class<?> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();

        Optional<Field> optPrimaryKeyField = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Id.class) &&
                        field.isAnnotationPresent(Column.class))
                .findFirst();

        if (optPrimaryKeyField.isEmpty()) {
            throw new Exception("Id não encontrado.");
        }

        return optPrimaryKeyField.get();
    }

}
