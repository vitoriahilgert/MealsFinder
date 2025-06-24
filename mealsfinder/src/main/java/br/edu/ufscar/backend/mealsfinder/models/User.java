package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.PersistenceFramework;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity(tableName = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

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
    private boolean isAccountConfirmed = false;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(name = "bio")
    private String bio;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Collection(targetEntity = Content.class, columnName = "created_content_ids")
    private Set<Content> createdContent = new HashSet<>();

    @Collection(targetEntity = Content.class, columnName = "liked_content_ids")
    private Set<Content> likedContent = new HashSet<>();

    @Collection(targetEntity = Content.class, columnName = "saved_content_ids")
    private Set<Content> savedContent = new HashSet<>();

    @Collection(targetEntity = User.class, columnName = "following_ids")
    private Set<User> following = new HashSet<>();

    @Collection(targetEntity = User.class, columnName = "followers_ids")
    private Set<User> followers = new HashSet<>();

    @Collection(targetEntity = User.class, columnName = "blocked_user_ids")
    private Set<User> blockedUsers = new HashSet<>();

    @Collection(targetEntity = User.class, columnName = "blocked_by_user_ids")
    private Set<User> blockedByUsers = new HashSet<>();

    public User(String email, String username, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.username = username;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = this.creationDate;
        this.isAccountConfirmed = false;
    }

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