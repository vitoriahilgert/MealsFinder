package br.edu.ufscar.backend.mealsfinder.dtos.review;

import br.edu.ufscar.backend.mealsfinder.models.entity.Review;

public class ReviewResponseDTO {
    private String id;
    private Double priceRate;
    private Double foodRate;
    private Double establishmentRate;
    private Double serviceRate;
    private Double deliveryRate;
    private boolean deliveryReview;

    public ReviewResponseDTO(Review review) {
        this.id = review.getId();
        this.priceRate = review.getPriceRate();
        this.foodRate = review.getFoodRate();
        this.establishmentRate = review.getEstablishmentRate();
        this.serviceRate = review.getServiceRate();
        this.deliveryRate = review.getDeliveryRate();
        this.deliveryReview = review.isDeliveryReview();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(Double priceRate) {
        this.priceRate = priceRate;
    }

    public Double getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(Double foodRate) {
        this.foodRate = foodRate;
    }

    public Double getEstablishmentRate() {
        return establishmentRate;
    }

    public void setEstablishmentRate(Double establishmentRate) {
        this.establishmentRate = establishmentRate;
    }

    public Double getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(Double serviceRate) {
        this.serviceRate = serviceRate;
    }

    public Double getDeliveryRate() {
        return deliveryRate;
    }

    public void setDeliveryRate(Double deliveryRate) {
        this.deliveryRate = deliveryRate;
    }

    public boolean isDeliveryReview() {
        return deliveryReview;
    }

    public void setDeliveryReview(boolean deliveryReview) {
        this.deliveryReview = deliveryReview;
    }
}
