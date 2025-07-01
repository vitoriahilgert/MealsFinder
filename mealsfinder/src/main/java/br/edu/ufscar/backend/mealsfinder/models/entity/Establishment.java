package br.edu.ufscar.backend.mealsfinder.models.entity;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentType;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "establishments")
@DiscriminatorValue("establishment")
@PrimaryKeyJoinColumn(name = "user_id")
public class Establishment extends User {

    @Column(name = "cnpj", unique = true, nullable = false)
    private String cnpj;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "establishment_type", nullable = false)
    private EstablishmentType establishmentType;

    @Column(name = "is_delivery", nullable = false)
    private boolean delivery;

    @Column(name = "is_presencial", nullable = false)
    private boolean presencial;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

    @Column(name = "rejections", nullable = false)
    private int rejections;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuCategory> menuCategories;

    @OneToMany(mappedBy = "reviewedEstablishment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "establishment_food_tags",
            joinColumns = @JoinColumn(name = "establishment_id"),
            inverseJoinColumns = @JoinColumn(name = "food_tag_id")
    )
    private Set<FoodTag> foodTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "establishment_service_tags",
            joinColumns = @JoinColumn(name = "establishment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_tag_id")
    )
    private Set<ServiceTag> serviceTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "establishment_environment_tags",
            joinColumns = @JoinColumn(name = "establishment_id"),
            inverseJoinColumns = @JoinColumn(name = "environment_tag_id")
    )
    private Set<EnvironmentTag> environmentTags;

    public Establishment() {
        super();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EstablishmentType getEstablishmentType() {
        return establishmentType;
    }

    public void setEstablishmentType(EstablishmentType establishmentType) {
        this.establishmentType = establishmentType;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isPresencial() {
        return presencial;
    }

    public void setPresencial(boolean presencial) {
        this.presencial = presencial;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public int getRejections() {
        return rejections;
    }

    public void setRejections(int rejections) {
        this.rejections = rejections;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<MenuCategory> getMenuCategories() {
        return menuCategories;
    }

    public void setMenuCategories(Set<MenuCategory> menuCategories) {
        this.menuCategories = menuCategories;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<FoodTag> getFoodTags() {
        return foodTags;
    }

    public void setFoodTags(Set<FoodTag> foodTags) {
        this.foodTags = foodTags;
    }

    public Set<ServiceTag> getServiceTags() {
        return serviceTags;
    }

    public void setServiceTags(Set<ServiceTag> serviceTags) {
        this.serviceTags = serviceTags;
    }

    public Set<EnvironmentTag> getEnvironmentTags() {
        return environmentTags;
    }

    public void setEnvironmentTags(Set<EnvironmentTag> environmentTags) {
        this.environmentTags = environmentTags;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
