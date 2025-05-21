package br.edu.ufscar.backend.mealsfinder.dtos.authentication;

import java.util.List;
import java.util.UUID;

public class ClientRegisterDTO {
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String profilePicUrl;
    private String bio;
    private List<UUID> followers;
    private List<String> likes;
    private List<String> dislikes;

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

    public List<UUID> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UUID> followers) {
        this.followers = followers;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }
}
