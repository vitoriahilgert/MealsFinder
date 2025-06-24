package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;
import java.util.UUID;

@Entity(name = "posts")
class Post {
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

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
}
