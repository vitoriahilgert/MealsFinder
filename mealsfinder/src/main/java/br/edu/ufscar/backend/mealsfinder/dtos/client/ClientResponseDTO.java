package br.edu.ufscar.backend.mealsfinder.dtos.client;

import br.edu.ufscar.backend.mealsfinder.models.entity.User;

import java.time.LocalDateTime;

public class ClientResponseDTO {
    private String id;
    private String email;
    private String profilePicureUrl;
    private LocalDateTime accountCreationDate;

    public ClientResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.accountCreationDate = user.getAccountCreationDate();
        this.profilePicureUrl = user.getProfilePictureUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicureUrl() {
        return profilePicureUrl;
    }

    public void setProfilePicureUrl(String profilePicureUrl) {
        this.profilePicureUrl = profilePicureUrl;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
}
