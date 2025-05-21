package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Client extends User {
    private List<FoodTypesEnum> likes;
    private List<FoodTypesEnum> dislikes;
    private List<User> following;
    private List<User> blockList;
    private List<Post> savedPosts;
    private List<Review> reviews;

    public Client() {
    }

    public Client(UUID id, String email, String phoneNumber, String username, String password, String profilePicUrl, boolean isAccountConfirmed, String confirmationCode, String bio, List<UUID> followers, List<FoodTypesEnum> likes, List<FoodTypesEnum> dislikes, List<User> following, List<User> blockList, List<Post> savedPosts, List<Review> reviews) {
        super(id, email, phoneNumber, username, password, profilePicUrl, isAccountConfirmed, confirmationCode, bio, followers);
        this.likes = likes;
        this.dislikes = dislikes;
        this.following = following;
        this.blockList = blockList;
        this.savedPosts = savedPosts;
        this.reviews = reviews;
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

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<User> blockList) {
        this.blockList = blockList;
    }

    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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
    
    public void followUser(User user) {
        if (!following.contains(user)) {
            following.add(user);
        }
    }
    
    public void blockUser(User user) {
        if (!blockList.contains(user)) {
            blockList.add(user);
        }
    }
    
    public void savePost(Post post) {
        if (!savedPosts.contains(post)) {
            savedPosts.add(post);
        }
    }
    
    public void addReview(Review review) {
        if (!reviews.contains(review)) {
            reviews.add(review);
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
                ", following=" + following.size() +
                '}';
    }
}
