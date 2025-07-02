package br.edu.ufscar.backend.mealsfinder.models.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "environment_tags")
@br.ufscar.pooa.Framework___POOA.persistence_framework.annotation.Entity(tableName = "environment_tags")

public class EnvironmentTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @br.ufscar.pooa.Framework___POOA.persistence_framework.annotation.Id
    @br.ufscar.pooa.Framework___POOA.persistence_framework.annotation.Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    @br.ufscar.pooa.Framework___POOA.persistence_framework.annotation.Column(name = "name")
    private String name;

    public EnvironmentTag() {
    }

    public EnvironmentTag(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvironmentTag that = (EnvironmentTag) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EnvironmentTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
