package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTagsEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;
import jakarta.persistence.*;
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
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String description;

    @OneToMany(mappedBy = "post")
    private List<User> likedBy = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private List<EstablishmentTagsEnum> establishmentTags = new ArrayList<>();
    private List<FoodTypesEnum> foodTags = new ArrayList<>();
    private List<String> pictureUrls = new ArrayList<>();
    

}
