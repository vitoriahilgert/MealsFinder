package br.edu.ufscar.backend.mealsfinder.framework.retentions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface Column {
    String name();
}
