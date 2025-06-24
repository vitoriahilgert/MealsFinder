package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;
import br.edu.ufscar.backend.mealsfinder.models.enums.UserRoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

@Entity(name = "users")
public abstract class User extends PersistenceFramework {
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "is_account_confirmed")
    private boolean isAccountConfirmed;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(name = "bio")
    private String bio;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private UserRoleEnum role;

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

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    //    public List<Client> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(List<Client> followers) {
//        this.followers = followers;
//    }
}
