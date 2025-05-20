package mealsfinder;

import java.util.UUID;

public class Review extends Content {
    private UUID establishmentId;
    
    private boolean isDelivery;
    private float foodRating;
    private float serviceRating;
    private float establishmentRating;
    
    public Review() {
        super();
    }

    public Review(UUID creatorId, String text, UUID establishmentId, 
                 boolean isDelivery, Integer foodRating, Integer serviceRating, 
                 Integer establishmentRating) {
        super(creatorId, text);
        this.isDelivery = isDelivery;
        this.establishmentId = establishmentId;
        this.foodRating = validateRating(foodRating);
        this.serviceRating = validateRating(serviceRating);
        this.establishmentRating = validateRating(establishmentRating);
    }

    public UUID getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(UUID establishmentId) {
        this.establishmentId = establishmentId;
    }

    public float getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(Integer foodRating) {
        this.foodRating = validateRating(foodRating);
    }

    public float getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(Integer serviceRating) {
        this.serviceRating = validateRating(serviceRating);
    }

    public float getEstablishmentRating() {
        return establishmentRating;
    }

    public void setEstablishmentRating(Integer establishmentRating) {
        this.establishmentRating = validateRating(establishmentRating);
    }
    
    public float getRatingAverage() {
        float sum = 0;

        sum += foodRating;
        sum += serviceRating;
        sum += establishmentRating;
        
        return (float) sum / 3;
    }
    
    /**
     * Validate that a rating is within acceptable range (1-5)
     * @param rating The rating to validate
     * @return The validated rating (clamped to 1-5 range)
     */
    private float validateRating(int rating) {
        return Math.max(1, Math.min(10, rating)); // Clamp between 1-5
    }

    @Override
    public String getContentType() {
        return "REVIEW";
    }
    
    @Override
    public String toString() {
        return "Review{" +
                "id=" + getId() +
                ", creator=" + getCreatorId() +
                ", isDelivery=" + isDelivery +
                ", establishment=" + establishmentId +
                ", rating=" + getRatingAverage() + "/10" +
                ", likes=" + getLikeCount() +
                '}';
    }
}
