package br.edu.ufscar.backend.mealsfinder.models.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Review review;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_food_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "food_tag_id")
    )
    private Set<FoodTag> foodTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_service_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "service_tag_id")
    )
    private Set<ServiceTag> serviceTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_environment_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "environment_tag_id")
    )
    private Set<EnvironmentTag> environmentTags;

    public Post() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Set<FoodTag> getFoodTags() {
        return foodTags;
    }

    public void setFoodTags(Set<FoodTag> foodTags) {
        this.foodTags = foodTags;
    }

    public Set<ServiceTag> getServiceTags() {
        return serviceTags;
    }

    public void setServiceTags(Set<ServiceTag> serviceTags) {
        this.serviceTags = serviceTags;
    }

    public Set<EnvironmentTag> getEnvironmentTags() {
        return environmentTags;
    }

    public void setEnvironmentTags(Set<EnvironmentTag> environmentTags) {
        this.environmentTags = environmentTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
