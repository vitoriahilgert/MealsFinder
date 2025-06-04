package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {
    private List<FoodTypesEnum> likes;
    private List<FoodTypesEnum> dislikes;

    @OneToMany(mappedBy = "client")
    private List<User> following = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<User> blockList = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Post> savedPosts = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Review> reviews = new ArrayList<>();
}
