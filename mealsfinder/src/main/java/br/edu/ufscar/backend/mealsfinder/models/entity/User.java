package br.edu.ufscar.backend.mealsfinder.models.entity;

import br.edu.ufscar.backend.mealsfinder.models.UserActivity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "account_creation_date", nullable = false, updatable = false)
    private LocalDateTime accountCreationDate;

    @Column(name = "is_account_confirmed", nullable = false)
    private boolean accountConfirmed;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserActivity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostLike> postLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentLike> commentLikes;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> followers;

    @OneToMany(mappedBy = "blocked", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlockedUser> blockers;

    public User() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.accountCreationDate == null) {
            this.accountCreationDate = LocalDateTime.now();
        }
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

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public boolean isAccountConfirmed() {
        return accountConfirmed;
    }

    public void setAccountConfirmed(boolean accountConfirmed) {
        this.accountConfirmed = accountConfirmed;
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<UserActivity> getActivities() {
        return activities;
    }

    public void setActivities(Set<UserActivity> activities) {
        this.activities = activities;
    }

    public Set<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Set<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public Set<CommentLike> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Set<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public Set<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follow> followers) {
        this.followers = followers;
    }

    public Set<BlockedUser> getBlockers() {
        return blockers;
    }

    public void setBlockers(Set<BlockedUser> blockers) {
        this.blockers = blockers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
