package br.edu.ufscar.backend.mealsfinder.framework;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

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
            this.insertSQL(entity, clazz.getSuperclass(), sql, conn);
            System.out.println("Inserido com sucesso na classe " + clazz.getSuperclass().getSimpleName());
        }

        String sql = this.buildInsertSQL(clazz);
        this.insertSQL(entity, clazz, sql, conn);
        System.out.println("Inserido com sucesso na classe " + clazz.getSimpleName());
    }

    private List<String> getColumnNames(Field[] fields) {
        List<String> columnNames = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name();
                if (columnName == null || columnName.isEmpty()) {
                    columnName = field.getName().toLowerCase();
                }
                columnNames.add(columnName);
            } else if (field.isAnnotationPresent(Collection.class)) {
                // Handle @Collection annotated fields
                Collection collectionAnnotation = field.getAnnotation(Collection.class);
                String columnName;
                if (collectionAnnotation.columnName() == null || collectionAnnotation.columnName().isEmpty()) {
                    columnName = field.getName().toLowerCase() + "_ids";
                } else {
                    columnName = collectionAnnotation.columnName();
                }
                columnNames.add(columnName);
            }
        }

        return columnNames;
    }

    private String buildInsertSQL(Class<?> clazz) {
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);

        String tableName = entityAnnotation.tableName();
        if (tableName == null || tableName.isEmpty()) {
            tableName = clazz.getSimpleName().toLowerCase();
        }

        Field[] fields = clazz.getDeclaredFields();
        List<String> columnsList = this.getColumnNames(fields);
        String columns = String.join(", ", columnsList);

        StringBuilder sql = (clazz.getSuperclass() == null || !clazz.getSuperclass().isAnnotationPresent(Entity.class)) ?
                new StringBuilder("INSERT INTO " + tableName + " (id, " + columns + ")  VALUES (") :
                new StringBuilder("INSERT INTO " + tableName + " (" + columns + ")  VALUES (");

        int size = (clazz.getSuperclass() == null || !clazz.getSuperclass().isAnnotationPresent(Entity.class)) ?
                columnsList.size() + 1 : columnsList.size();

        for (int i = 0; i < size; i++) {
            sql.append("?");
            if (i != size - 1) { sql.append(", "); }
        }

        sql.append(");");

        return sql.toString();
    }

    private <T> void insertSQL(T entity, Class<?> targetClass, String sql, Connection connection) throws Exception {
        Field[] fields = targetClass.getDeclaredFields();
        List<String> columnsList = this.getColumnNames(fields);

        PreparedStatement statement = connection.prepareStatement(sql);

        int paramIndex = 1;

        // Add ID if this is a root entity (no JPA entity superclass)
        if (targetClass.getSuperclass() == null || !targetClass.getSuperclass().isAnnotationPresent(Entity.class)) {
            statement.setString(paramIndex++, UUID.randomUUID().toString());
        }

        // Process each field
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Collection.class)) {
                Object value;

                if (field.isAnnotationPresent(Collection.class)) {
                    // Convert collection to whitespace-separated string
                    value = convertCollectionToString(field.get(entity));
                } else {
                    value = field.get(entity);
                }

                statement.setObject(paramIndex++, value);
            }
        }

        statement.executeUpdate();
    }

    /**
     * Converts a collection of entities to a whitespace-separated string of IDs
     */
    private String convertCollectionToString(Object collectionObj) throws Exception {
        if (collectionObj == null) {
            return "";
        }

        if (!(collectionObj instanceof java.util.Collection)) {
            throw new Exception("@Collection annotation can only be used on Collection fields");
        }

        java.util.Collection<?> collection = (java.util.Collection<?>) collectionObj;
        List<String> ids = new ArrayList<>();

        for (Object item : collection) {
            String id = extractIdFromEntity(item);
            if (id != null && !id.isEmpty()) {
                ids.add(id);
            }
        }

        return String.join(" ", ids);
    }

    /**
     * Extracts the ID from an entity object
     * Assumes the entity has an 'id' field or getId() method
     */
    private String extractIdFromEntity(Object entity) throws Exception {
        if (entity == null) {
            return null;
        }

        Class<?> clazz = entity.getClass();

        // Try to find 'id' field first
        try {
            Field idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(entity);
            return idValue != null ? idValue.toString() : null;
        } catch (NoSuchFieldException e) {
            // Field not found, try getId() method
        }

        // Try to find getId() method
        try {
            Method getIdMethod = clazz.getMethod("getId");
            Object idValue = getIdMethod.invoke(entity);
            return idValue != null ? idValue.toString() : null;
        } catch (NoSuchMethodException e) {
            throw new Exception("Entity " + clazz.getSimpleName() + " must have either an 'id' field or 'getId()' method");
        }
    }

    /**
     * Converts a whitespace-separated string of IDs back to a collection
     * This would be used when loading entities from the database
     */
    public List<String> convertStringToIdList(String idsString) {
        if (idsString == null || idsString.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(idsString.trim().split("\\s+"))
                .filter(id -> !id.isEmpty())
                .collect(Collectors.toList());
    }
}
