package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "is_account_confirmed", nullable = false)
    private boolean isAccountConfirmed = false;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Content> createdContent = new HashSet<>();

    @ManyToMany(mappedBy = "likes")
    private Set<Content> likedContent = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_saved_content",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private Set<Content> savedContent = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_blocks",
            joinColumns = @JoinColumn(name = "blocker_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_id")
    )
    private Set<User> blockedUsers = new HashSet<>();

    @ManyToMany(mappedBy = "blockedUsers")
    private Set<User> blockedByUsers = new HashSet<>();

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = this.creationDate;
        this.isAccountConfirmed = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }

    public boolean followUser(User user) {
        if (user == null || user.equals(this) || this.hasBlocked(user) || user.hasBlocked(this)) {
            return false;
        }
        boolean added = this.following.add(user);
        if (added) {
            user.getFollowers().add(this);
        }
        return added;
    }

    public boolean unfollowUser(User user) {
        boolean removed = this.following.remove(user);
        if (removed) {
            user.getFollowers().remove(this);
        }
        return removed;
    }

    public boolean isFollowing(User user) {
        return this.following.contains(user);
    }

    public boolean isFollowedBy(User user) {
        return this.followers.contains(user);
    }

    public int getFollowingCount() {
        return this.following.size();
    }

    public int getFollowersCount() {
        return this.followers.size();
    }

    public boolean blockUser(User user) {
        if (user == null || user.equals(this)) {
            return false;
        }
        this.unfollowUser(user);
        user.unfollowUser(this);

        boolean blocked = this.blockedUsers.add(user);
        if (blocked) {
            user.getBlockedByUsers().add(this);
        }
        return blocked;
    }

    public boolean unblockUser(User user) {
        boolean unblocked = this.blockedUsers.remove(user);
        if (unblocked) {
            user.getBlockedByUsers().remove(this);
        }
        return unblocked;
    }

    public boolean hasBlocked(User user) {
        return this.blockedUsers.contains(user);
    }

    public boolean isBlockedBy(User user) {
        return this.blockedByUsers.contains(user);
    }

    public boolean saveContent(Content content) {
        return this.savedContent.add(content);
    }

    public boolean unsaveContent(Content content) {
        return this.savedContent.remove(content);
    }

    public boolean hasSaved(Content content) {
        return this.savedContent.contains(content);
    }

    public boolean likeContent(Content content) {
        boolean liked = content.addLike(this);
        if (liked) {
            this.likedContent.add(content);
        }
        return liked;
    }

    public boolean unlikeContent(Content content) {
        boolean unliked = content.removeLike(this);
        if (unliked) {
            this.likedContent.remove(content);
        }
        return unliked;
    }

    public boolean hasLiked(Content content) {
        return this.likedContent.contains(content);
    }

    public int getCreatedContentCount() {
        return this.createdContent.size();
    }

    public int getLikedContentCount() {
        return this.likedContent.size();
    }

    public int getSavedContentCount() {
        return this.savedContent.size();
    }

    public abstract String getUserType();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", type=" + getUserType() +
                ", followers=" + getFollowersCount() +
                ", following=" + getFollowingCount() +
                '}';
    }
}