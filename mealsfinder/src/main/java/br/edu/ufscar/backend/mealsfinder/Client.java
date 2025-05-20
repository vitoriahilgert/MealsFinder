package br.edu.ufscar.backend.mealsfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client extends User {
    private List<FoodTypesEnum> likes;
    private List<FoodTypesEnum> dislikes;
    private List<UUID> following;
    private List<UUID> blockList;
    private List<UUID> savedPosts;
    private List<UUID> reviews;

    public Client() {
        super();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
        this.following = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.savedPosts = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Client(String email, String phoneNumber, String username, String password) {
        super(email, phoneNumber, username, password);
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
        this.following = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.savedPosts = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public List<FoodTypesEnum> getLikes() {
        return likes;
    }

    public void setLikes(List<FoodTypesEnum> likes) {
        this.likes = likes;
    }
    
    public void addLike(FoodTypesEnum foodType) {
        if (!likes.contains(foodType)) {
            likes.add(foodType);
        }
    }

    public List<FoodTypesEnum> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<FoodTypesEnum> dislikes) {
        this.dislikes = dislikes;
    }
    
    public void addDislike(FoodTypesEnum foodType) {
        if (!dislikes.contains(foodType)) {
            dislikes.add(foodType);
        }
    }

    public List<UUID> getFollowing() {
        return following;
    }

    public void setFollowing(List<UUID> following) {
        this.following = following;
    }
    
    public void followUser(UUID userId) {
        if (!following.contains(userId)) {
            following.add(userId);
        }
    }
    
    public void unfollowUser(UUID userId) {
        following.remove(userId);
    }

    public List<UUID> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<UUID> blockList) {
        this.blockList = blockList;
    }
    
    public void blockUser(UUID userId) {
        if (!blockList.contains(userId)) {
            blockList.add(userId);
            unfollowUser(userId);
        }
    }
    
    public void unblockUser(UUID userId) {
        blockList.remove(userId);
    }

    public List<UUID> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<UUID> savedPosts) {
        this.savedPosts = savedPosts;
    }
    
    public void savePost(UUID postId) {
        if (!savedPosts.contains(postId)) {
            savedPosts.add(postId);
        }
    }
    
    public void unsavePost(UUID postId) {
        savedPosts.remove(postId);
    }

    public List<UUID> getReviews() {
        return reviews;
    }

    public void setReviews(List<UUID> reviews) {
        this.reviews = reviews;
    }
    
    public void addReview(UUID reviewId) {
        if (!reviews.contains(reviewId)) {
            reviews.add(reviewId);
        }
    }
    
    public void removeReview(UUID reviewId) {
        reviews.remove(reviewId);
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
                ", following=" + following.size() +
                '}';
    }
}
