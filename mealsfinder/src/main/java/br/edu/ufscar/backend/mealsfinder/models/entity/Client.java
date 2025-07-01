package br.edu.ufscar.backend.mealsfinder.models.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "clients")
@DiscriminatorValue("client")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_liked_food_tags",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "food_tag_id")
    )
    private Set<FoodTag> likedFoodTags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_disliked_food_tags",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "food_tag_id")
    )
    private Set<FoodTag> dislikedFoodTags;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SavedPost> savedPosts;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> following;

    @OneToMany(mappedBy = "blocker", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlockedUser> blockedUsers;

    public Client() {
        super();
    }

    public Set<FoodTag> getLikedFoodTags() {
        return likedFoodTags;
    }

    public void setLikedFoodTags(Set<FoodTag> likedFoodTags) {
        this.likedFoodTags = likedFoodTags;
    }

    public Set<FoodTag> getDislikedFoodTags() {
        return dislikedFoodTags;
    }

    public void setDislikedFoodTags(Set<FoodTag> dislikedFoodTags) {
        this.dislikedFoodTags = dislikedFoodTags;
    }

    public Set<SavedPost> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(Set<SavedPost> savedPosts) {
        this.savedPosts = savedPosts;
    }

    public Set<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Follow> following) {
        this.following = following;
    }

    public Set<BlockedUser> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(Set<BlockedUser> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
