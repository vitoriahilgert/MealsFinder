package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "posts")
public class Post {
    @Column(name = "id")
    @Id
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "post")
//    private List<User> likedBy = new ArrayList<>();

//    @OneToMany(mappedBy = "post")
//    private List<Comment> comments = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

//    private List<EstablishmentTagsEnum> establishmentTags = new ArrayList<>();
//    private List<FoodTypesEnum> foodTags = new ArrayList<>();
//    private List<String> pictureUrls = new ArrayList<>();

    public Post() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
