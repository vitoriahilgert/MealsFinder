package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
public abstract class User extends PersistenceFramework {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String phoneNumber;

    @Column(unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column
    private String profilePicUrl;

    @Column
    private boolean isAccountConfirmed;

    @Column
    private String confirmationCode;

    @Column
    private String bio;

//    @OneToMany(mappedBy = "user")
//    private List<Client> followers = new ArrayList<>();

    public User() {
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

//    public List<Client> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(List<Client> followers) {
//        this.followers = followers;
//    }
}
