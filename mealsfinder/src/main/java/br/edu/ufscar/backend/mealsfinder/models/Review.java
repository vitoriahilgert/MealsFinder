package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;

@Entity(name = "reviews")
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
