package br.edu.ufscar.backend.mealsfinder.framework.retentions;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Collection {
    /**
     * The target entity class for the collection relationship
     */
    Class<?> targetEntity();

    /**
     * Optional: specify the column name in the database
     * If not specified, will use field name with "_ids" suffix
     */
    String columnName() default "";
}