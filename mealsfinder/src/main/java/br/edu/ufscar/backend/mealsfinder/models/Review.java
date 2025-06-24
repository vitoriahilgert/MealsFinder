package br.edu.ufscar.backend.mealsfinder.models;

import jakarta.persistence.*;

@Entity
class Review extends Post {
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private boolean isDelivery;

    @Column
    private Double establishmentRating;

    @Column
    private Double serviceRating;

    @Column
    private Double deliveryRating;

    public Review() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public Double getEstablishmentRating() {
        return establishmentRating;
    }

    public void setEstablishmentRating(Double establishmentRating) {
        this.establishmentRating = establishmentRating;
    }

    public Double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(Double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public Double getDeliveryRating() {
        return deliveryRating;
    }

    public void setDeliveryRating(Double deliveryRating) {
        this.deliveryRating = deliveryRating;
    }
}
