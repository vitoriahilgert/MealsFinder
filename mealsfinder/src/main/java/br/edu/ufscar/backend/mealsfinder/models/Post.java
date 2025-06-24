package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTagsEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "posts")
@DiscriminatorValue("POST")
@Getter
@Setter
@NoArgsConstructor
public class Post extends Content {

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection(targetClass = EstablishmentTagsEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "post_establishment_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private Set<EstablishmentTagsEnum> establishmentTags = new HashSet<>();

    @ElementCollection(targetClass = FoodTypesEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "post_food_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private Set<FoodTypesEnum> foodTags = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "post_pictures", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "picture_url")
    private Set<String> pictureUrls = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "reviewedPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    public Post(User creator, String text, String description) {
        super(creator, text);
        this.description = description;
    }

    @Override
    public String getContentType() {
        return "POST";
    }

    public int getCommentCount() {
        return this.comments.size();
    }

    public int getReviewCount() {
        return this.reviews.size();
    }

    public double getAverageRating() {
        return this.reviews.stream()
                .mapToDouble(Review::getOverallDetailedRating)
                .average()
                .orElse(0.0);
    }
}
