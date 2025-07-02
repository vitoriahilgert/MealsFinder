package br.edu.ufscar.backend.mealsfinder.framework.retentions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Optional;

@Retention(RetentionPolicy.RUNTIME)

public @interface Column {
    String name();
    boolean unique() default false;
}
