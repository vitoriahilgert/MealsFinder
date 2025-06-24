package br.edu.ufscar.backend.mealsfinder.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "content")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToMany
    @JoinTable(
            name = "content_likes",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    @ManyToMany(mappedBy = "savedContent")
    private Set<User> savedByUsers = new HashSet<>();

    public Content(User creator, String text) {
        this.creator = creator;
        this.text = text;
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = this.creationDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }

    public boolean addLike(User user) {
        return this.likes.add(user);
    }

    public boolean removeLike(User user) {
        return this.likes.remove(user);
    }

    public boolean isLikedBy(User user) {
        return this.likes.contains(user);
    }

    public int getLikeCount() {
        return this.likes.size();
    }

    public boolean isSavedBy(User user) {
        return this.savedByUsers.contains(user);
    }

    public int getSavedCount() {
        return this.savedByUsers.size();
    }

    public abstract String getContentType();

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", creator=" + (creator != null ? creator.getId() : "null") +
                ", likes=" + getLikeCount() +
                ", created=" + creationDate +
                ", type=" + getContentType() +
                '}';
    }
}