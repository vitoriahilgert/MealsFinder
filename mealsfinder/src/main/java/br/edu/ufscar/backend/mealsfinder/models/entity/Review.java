package br.edu.ufscar.backend.mealsfinder.models.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "post_id")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_establishment_id", nullable = false)
    private Establishment reviewedEstablishment;

    @Column(name = "price_rate")
    private Double priceRate;

    @Column(name = "food_rate")
    private Double foodRate;

    @Column(name = "establishment_rate")
    private Double establishmentRate;

    @Column(name = "service_rate")
    private Double serviceRate;

    @Column(name = "delivery_rate")
    private Double deliveryRate;

    @Column(name = "is_delivery_review", nullable = false)
    private boolean deliveryReview;

    public Review() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Establishment getReviewedEstablishment() {
        return reviewedEstablishment;
    }

    public void setReviewedEstablishment(Establishment reviewedEstablishment) {
        this.reviewedEstablishment = reviewedEstablishment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
