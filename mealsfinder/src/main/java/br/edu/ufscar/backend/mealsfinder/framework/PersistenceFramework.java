package br.edu.ufscar.backend.mealsfinder.framework;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class PersistenceFramework {
    private String databasePath;

    public void setDBAbsolutePath(String dbAbsolutePath) {
        this.databasePath = dbAbsolutePath;
    }

    // insert e related methods:
    public <T> UUID insert(T entity) throws Exception {
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
        return entityId;
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


    // findByPrimaryKey e related methods:
    public <T, ID> Optional<T> findByPrimaryKey(Class<T> clazz, ID id) throws Exception {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("A classe não é uma entidade JPA.");
        }

        String sql = buildSelectSQL(clazz);
        try (Connection conn = DriverManager.getConnection(databasePath);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    T instance = clazz.getDeclaredConstructor().newInstance();
                    populateObjectFromResultSet(instance, clazz, rs);
                    return Optional.of(instance);
                }
            }
        }
        return Optional.empty();
    }

    private <T> void populateObjectFromResultSet(T instance, Class<?> clazz, ResultSet rs) throws Exception {
        if (clazz.getSuperclass() != null && clazz.getSuperclass().isAnnotationPresent(Entity.class)) {
            populateObjectFromResultSet(instance, clazz.getSuperclass(), rs);
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                Object value;
                try {
                    value = rs.getObject(column.name());
                } catch (SQLException e) {
                    continue;
                }

                if (value == null) {
                    continue;
                }

                if (field.getType().isEnum()) {
                    if (value instanceof Number) {
                        int ordinal = ((Number) value).intValue();
                        Object[] enumConstants = field.getType().getEnumConstants();
                        if (ordinal >= 0 && ordinal < enumConstants.length) {
                            field.set(instance, enumConstants[ordinal]);
                        }
                    } else if (value instanceof String) {
                        field.set(instance, Enum.valueOf((Class<Enum>) field.getType(), (String) value));
                    }
                } else if (value instanceof Number) {
                    Number numValue = (Number) value;
                    Class<?> fieldType = field.getType();
                    if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                        field.set(instance, numValue.longValue());
                    } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                        field.set(instance, numValue.intValue());
                    } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                        field.set(instance, numValue.doubleValue());
                    } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
                        field.set(instance, numValue.shortValue());
                    } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                        field.set(instance, numValue.floatValue());
                    } else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
                        field.set(instance, numValue.byteValue());
                    } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                        field.set(instance, numValue.intValue() != 0);
                    } else {
                        field.set(instance, value);
                    }
                } else if (value instanceof String && field.getType().equals(UUID.class)) {
                    field.set(instance, UUID.fromString((String) value));
                } else {
                    field.set(instance, value);
                }

            } else if (field.isAnnotationPresent(Embedded.class)) {
                Object embeddedInstance = field.getType().getDeclaredConstructor().newInstance();
                populateObjectFromResultSet(embeddedInstance, field.getType(), rs);
                field.set(instance, embeddedInstance);
            }
        }
    }

    private String buildSelectSQL(Class<?> clazz) throws Exception {
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);

        String mainTable = entityAnnotation.name();
        Class<?> superclass = clazz.getSuperclass();

        if (superclass != null && superclass.isAnnotationPresent(Entity.class)) {
            // Pega a coluna com a chave primaria na classe pai
            String superTable = superclass.getAnnotation(Entity.class).name();
            String superTablePkName = getPrimaryKeyColumnName(superclass);
            return String.format("SELECT * FROM %s t1 JOIN %s t2 ON t1.user_id = t2.%s WHERE t2.%s = ?",
                    mainTable, superTable, superTablePkName, superTablePkName);
        } else {
            // Se não herda nenhuma classe apenas pega a coluna com a chave primaria
            String pkColumnName = getPrimaryKeyColumnName(clazz);
            return String.format("SELECT * FROM %s WHERE %s = ?", mainTable, pkColumnName);
        }
    }

    private String getPrimaryKeyColumnName(Class<?> clazz) throws Exception {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    return field.getAnnotation(Column.class).name();
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        throw new Exception("Nenhum campo com @Id encontrado na entidade " + clazz.getSimpleName());
    }

    public <T> List<T> findAll(Class<T> clazz) throws Exception {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("A classe não é uma entidade JPA.");
        }

        List<T> results = new ArrayList<>();
        String sql = buildFindAllSelectSQL(clazz);

        try (Connection conn = DriverManager.getConnection(databasePath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                populateObjectFromResultSet(instance, clazz, rs);
                results.add(instance);
            }
        }
        return results;
    }

    private String buildFindAllSelectSQL(Class<?> clazz) throws Exception {
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);
        String mainTable = entityAnnotation.name();
        Class<?> superclass = clazz.getSuperclass();

        if (superclass != null && superclass.isAnnotationPresent(Entity.class)) {
            String superTable = superclass.getAnnotation(Entity.class).name();
            String superTablePkName = getPrimaryKeyColumnName(superclass);
            return String.format("SELECT * FROM %s t1 JOIN %s t2 ON t1.user_id = t2.%s",
                    mainTable, superTable, superTablePkName);
        } else {
            return String.format("SELECT * FROM %s", mainTable);
        }
    }

}
