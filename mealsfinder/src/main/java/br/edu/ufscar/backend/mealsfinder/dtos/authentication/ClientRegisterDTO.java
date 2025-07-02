package br.edu.ufscar.backend.mealsfinder.dtos.authentication;

import br.edu.ufscar.backend.mealsfinder.models.entity.FoodTag;

import java.util.Set;

public class ClientRegisterDTO {
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String profilePicUrl;
    private String bio;
    private Set<FoodTag> likes;
    private Set<FoodTag> dislikes;

    public ClientRegisterDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<FoodTag> getLikes() {
        return likes;
    }

    public void setLikes(Set<FoodTag> likes) {
        this.likes = likes;
    }

    public Set<FoodTag> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<FoodTag> dislikes) {
        this.dislikes = dislikes;
    }
}
