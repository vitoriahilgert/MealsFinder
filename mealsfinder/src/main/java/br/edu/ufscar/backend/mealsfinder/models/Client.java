package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(tableName = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    // Store food preferences as whitespace-separated enum strings
    @Column(name = "food_likes")
    private String foodLikesString = "";

    @Column(name = "food_dislikes")
    private String foodDislikesString = "";

    // Transient fields for easy access (not persisted)
    private transient Set<FoodTypesEnum> foodLikes = new HashSet<>();
    private transient Set<FoodTypesEnum> foodDislikes = new HashSet<>();

    public Client(String email, String username, String password) {
        super(email, username, password);
        this.foodLikes = new HashSet<>();
        this.foodDislikes = new HashSet<>();
    }

    @Override
    public String getUserType() {
        return "CLIENT";
    }

    // Methods to sync between enum sets and string representations
    private void syncFoodLikesToString() {
        this.foodLikesString = this.foodLikes.stream()
                .map(Enum::name)
                .collect(Collectors.joining(" "));
    }

    private void syncFoodDislikesToString() {
        this.foodDislikesString = this.foodDislikes.stream()
                .map(Enum::name)
                .collect(Collectors.joining(" "));
    }

    private void syncStringToFoodLikes() {
        if (this.foodLikesString != null && !this.foodLikesString.trim().isEmpty()) {
            this.foodLikes = Arrays.stream(this.foodLikesString.trim().split("\\s+"))
                    .map(FoodTypesEnum::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.foodLikes = new HashSet<>();
        }
    }

    private void syncStringToFoodDislikes() {
        if (this.foodDislikesString != null && !this.foodDislikesString.trim().isEmpty()) {
            this.foodDislikes = Arrays.stream(this.foodDislikesString.trim().split("\\s+"))
                    .map(FoodTypesEnum::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.foodDislikes = new HashSet<>();
        }
    }

    // Call this method after loading from database
    public void initializeFromDatabase() {
        syncStringToFoodLikes();
        syncStringToFoodDislikes();
    }

    // Call this method before saving to database
    public void prepareForDatabase() {
        syncFoodLikesToString();
        syncFoodDislikesToString();
    }

    public Set<FoodTypesEnum> getFoodLikes() {
        if (this.foodLikes == null) {
            syncStringToFoodLikes();
        }
        return this.foodLikes;
    }

    public Set<FoodTypesEnum> getFoodDislikes() {
        if (this.foodDislikes == null) {
            syncStringToFoodDislikes();
        }
        return this.foodDislikes;
    }

    public boolean addFoodLike(FoodTypesEnum foodType) {
        getFoodDislikes().remove(foodType);
        boolean added = getFoodLikes().add(foodType);
        if (added) {
            syncFoodLikesToString();
            syncFoodDislikesToString();
        }
        return added;
    }

    public boolean removeFoodLike(FoodTypesEnum foodType) {
        boolean removed = getFoodLikes().remove(foodType);
        if (removed) {
            syncFoodLikesToString();
        }
        return removed;
    }

    public boolean addFoodDislike(FoodTypesEnum foodType) {
        getFoodLikes().remove(foodType);
        boolean added = getFoodDislikes().add(foodType);
        if (added) {
            syncFoodLikesToString();
            syncFoodDislikesToString();
        }
        return added;
    }

    public boolean removeFoodDislike(FoodTypesEnum foodType) {
        boolean removed = getFoodDislikes().remove(foodType);
        if (removed) {
            syncFoodDislikesToString();
        }
        return removed;
    }

    public boolean likesFoodType(FoodTypesEnum foodType) {
        return getFoodLikes().contains(foodType);
    }

    public boolean dislikesFoodType(FoodTypesEnum foodType) {
        return getFoodDislikes().contains(foodType);
    }
}
