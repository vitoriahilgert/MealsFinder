package br.edu.ufscar.backend.mealsfinder.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class User {
    private UUID id;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String profilePicUrl;
    private boolean isAccountConfirmed;
    private String confirmationCode;
    private String bio;
    private List<UUID> followers;

    public User() {
    }

    public User(UUID id, String email, String phoneNumber, String username, String password, String profilePicUrl, boolean isAccountConfirmed, String confirmationCode, String bio, List<UUID> followers) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.profilePicUrl = profilePicUrl;
        this.isAccountConfirmed = isAccountConfirmed;
        this.confirmationCode = confirmationCode;
        this.bio = bio;
        this.followers = followers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isAccountConfirmed() {
        return isAccountConfirmed;
    }

    public void setAccountConfirmed(boolean accountConfirmed) {
        isAccountConfirmed = accountConfirmed;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
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
    
    public void addFollower(UUID followerId) {
        if (!followers.contains(followerId)) {
            followers.add(followerId);
        }
    }
    
    public void removeFollower(UUID followerId) {
        followers.remove(followerId);
    }
    
    // Abstract methods that subclasses must implement
    public abstract String getUserType();
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
