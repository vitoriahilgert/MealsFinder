package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "client")
@jakarta.persistence.Entity
public class Client extends User {
    // private List<FoodTypesEnum> likes;
    // private List<FoodTypesEnum> dislikes;

//    @OneToMany(mappedBy = "client")
//    private List<User> following = new ArrayList<>();
//
//    @OneToMany(mappedBy = "client")
//    private List<User> blockList = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "client")
//    private List<Post> savedPosts = new ArrayList<>();
//
//    @OneToMany(mappedBy = "client")
//    private List<Review> reviews = new ArrayList<>();

//    public List<FoodTypesEnum> getLikes() {
//        return likes;
//    }
//
//    public void setLikes(List<FoodTypesEnum> likes) {
//        this.likes = likes;
//    }
//
//    public List<FoodTypesEnum> getDislikes() {
//        return dislikes;
//    }
//
//    public void setDislikes(List<FoodTypesEnum> dislikes) {
//        this.dislikes = dislikes;
//    }

//    public List<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(List<User> following) {
//        this.following = following;
//    }
//
//    public List<User> getBlockList() {
//        return blockList;
//    }
//
//    public void setBlockList(List<User> blockList) {
//        this.blockList = blockList;
//    }

//    public List<Post> getSavedPosts() {
//        return savedPosts;
//    }
//
//    public void setSavedPosts(List<Post> savedPosts) {
//        this.savedPosts = savedPosts;
//    }
//
//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }
}
