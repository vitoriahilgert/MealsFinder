package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(tableName = "content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Content {
    @Column(name = "id")
    private UUID id;

    @Column(name = "text")
    private String text;

    @Collection(targetEntity = User.class, columnName = "liked_by_ids")
    private Set<User> likes = new HashSet<>();

    @Column(name = "creator_id")
    private User creator;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    public Content(User creator, String text) {
        this.creator = creator;
        this.text = text;
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = this.creationDate;
    }

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