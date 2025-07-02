package br.edu.ufscar.backend.mealsfinder.models.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "clients")
@DiscriminatorValue("client")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_liked_food_tags",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "food_tag_id")
    )
    private Set<FoodTag> likedFoodTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_disliked_food_tags",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "food_tag_id")
    )
    private Set<FoodTag> dislikedFoodTags;

    public Client() {
        super();
    }

    public Set<FoodTag> getLikedFoodTags() {
        return likedFoodTags;
    }

    public void setLikedFoodTags(Set<FoodTag> likedFoodTags) {
        this.likedFoodTags = likedFoodTags;
    }

    public Set<FoodTag> getDislikedFoodTags() {
        return dislikedFoodTags;
    }

    public void setDislikedFoodTags(Set<FoodTag> dislikedFoodTags) {
        this.dislikedFoodTags = dislikedFoodTags;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
