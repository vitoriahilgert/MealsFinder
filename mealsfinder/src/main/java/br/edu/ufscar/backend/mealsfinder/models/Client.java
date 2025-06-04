package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;
import br.edu.ufscar.backend.mealsfinder.services.commentservice.ContentRelationshipService;

import java.util.List;
import java.util.UUID;

public class Client extends User {
    private List<FoodTypesEnum> likes;
    private List<FoodTypesEnum> dislikes;

    private ContentRelationshipService contentRelationshipService;

    public Client() {
    }

    public Client(UUID id, String email, String phoneNumber, String username, String password, String profilePicUrl, boolean isAccountConfirmed, String confirmationCode, String bio, List<FoodTypesEnum> likes, List<FoodTypesEnum> dislikes) {
        super(id, email, phoneNumber, username, password, profilePicUrl, isAccountConfirmed, confirmationCode, bio);
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public List<FoodTypesEnum> getLikes() {
        return likes;
    }

    public void setLikes(List<FoodTypesEnum> likes) {
        this.likes = likes;
    }

    public List<FoodTypesEnum> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<FoodTypesEnum> dislikes) {
        this.dislikes = dislikes;
    }

    public List<UUID> getFollowing() {
        return userRelationshipService.getFollowing(this.getId());
    }

    public List<UUID> getBlockList() {
        return userRelationshipService.getBlockedUsers(this.getId());
    }

    public List<UUID> getSavedPosts() {
        return contentRelationshipService.getSavedPosts(this.getId());
    }

    public List<UUID> getReviews() {
        return contentRelationshipService.getReviewsByUser(this.getId());
    }

    public void addLike(FoodTypesEnum foodType) {
        if (!likes.contains(foodType)) {
            likes.add(foodType);
        }
    }
    
    public void addDislike(FoodTypesEnum foodType) {
        if (!dislikes.contains(foodType)) {
            dislikes.add(foodType);
        }
    }
    
    public void followUser(UUID userId) {
        if (!userRelationshipService.isFollowing(this.getId(), userId) || !userRelationshipService.isBlocked(this.getId(), userId)) {
            userRelationshipService.followUser(this.getId(), userId);
        }
    }
    
    public void blockUser(UUID userId) {
        if (!userRelationshipService.isBlocked(this.getId(), userId)) {
            userRelationshipService.blockUser(this.getId(), userId);
        }
    }
    
    public void savePost(UUID postId) {
        if (!contentRelationshipService.isPostSavedByUser(this.getId(), postId)) {
            contentRelationshipService.savePost(this.getId(), postId);
        }
    }

    @Override
    public String getUserType() {
        return "CLIENT";
    }
    
    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", likes=" + likes.size() +
                ", following=" + userRelationshipService.getFollowing(this.getId()).size() +
                '}';
    }
}
