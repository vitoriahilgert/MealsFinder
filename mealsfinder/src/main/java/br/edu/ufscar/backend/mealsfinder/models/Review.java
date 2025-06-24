package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;

@Entity(tableName = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review extends Post {

    @Column(name = "price")
    private Double price;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "is_delivery")
    private boolean isDelivery;

    @Column(name = "establishment_rating")
    private Double establishmentRating;

    @Column(name = "service_rating")
    private Double serviceRating;

    @Column(name = "delivery_rating")
    private Double deliveryRating;

    @Column(name = "reviewed_post_id")
    private Post reviewedPost;

    public Review(User creator, String text, String description, Double price, Double rating, boolean isDelivery) {
        super(creator, text, description);
        this.price = price;
        this.rating = rating;
        this.isDelivery = isDelivery;
    }

    @Override
    public String getContentType() {
        return "REVIEW";
    }

    public boolean isValidRating() {
        return rating != null && rating >= 0.0 && rating <= 5.0;
    }

    public boolean hasDetailedRatings() {
        return establishmentRating != null || serviceRating != null || deliveryRating != null;
    }

    public double getOverallDetailedRating() {
        double sum = 0.0;
        int count = 0;

        if (establishmentRating != null) {
            sum += establishmentRating;
            count++;
        }
        if (serviceRating != null) {
            sum += serviceRating;
            count++;
        }
        if (deliveryRating != null && isDelivery) {
            sum += deliveryRating;
            count++;
        }

        return count > 0 ? sum / count : 0.0;
    }
}
