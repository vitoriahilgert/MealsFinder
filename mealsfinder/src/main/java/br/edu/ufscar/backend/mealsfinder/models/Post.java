package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.ImageType;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;

@Entity(tableName = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post extends Content {

    @Column(name = "description")
    private String description;

//    @ElementCollection(targetClass = EstablishmentTagsEnum.class)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "post_establishment_tags", joinColumns = @JoinColumn(name = "post_id"))
//    @Column(name = "tag")
//    private Set<EstablishmentTagsEnum> establishmentTags = new HashSet<>();
//
//    @ElementCollection(targetClass = FoodTypesEnum.class)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "post_food_tags", joinColumns = @JoinColumn(name = "post_id"))
//    @Column(name = "tag")
//    private Set<FoodTypesEnum> foodTags = new HashSet<>();
//
//    @Column(name = "picture_url")
//    private Set<String> pictureUrls = new HashSet<>();

    @Collection(targetEntity = Comment.class, columnName = "comments_ids")
    private Set<Comment> comments = new HashSet<>();

    @Collection(targetEntity = Review.class, columnName = "reviews_ids")
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
