package br.edu.ufscar.backend.mealsfinder.dtos.review;

import java.util.Set;

public class CreateReviewRequestDTO {
    private String description;

    private Set<String> foodTagIds;
    private Set<String> serviceTagIds;
    private Set<String> environmentTagIds;
    private String establishmentId;
    private String clientId;
    private Double priceRate;
    private Double foodRate;
    private Double establishmentRate;
    private Double serviceRate;
    private Double deliveryRate;
    private boolean deliveryReview;

    public CreateReviewRequestDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getFoodTagIds() {
        return foodTagIds;
    }

    public void setFoodTagIds(Set<String> foodTagIds) {
        this.foodTagIds = foodTagIds;
    }

    public Set<String> getServiceTagIds() {
        return serviceTagIds;
    }

    public void setServiceTagIds(Set<String> serviceTagIds) {
        this.serviceTagIds = serviceTagIds;
    }

    public Set<String> getEnvironmentTagIds() {
        return environmentTagIds;
    }

    public void setEnvironmentTagIds(Set<String> environmentTagIds) {
        this.environmentTagIds = environmentTagIds;
    }

    public String getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(String establishmentId) {
        this.establishmentId = establishmentId;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}