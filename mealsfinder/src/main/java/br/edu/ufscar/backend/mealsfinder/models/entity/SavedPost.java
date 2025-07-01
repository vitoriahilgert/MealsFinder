package br.edu.ufscar.backend.mealsfinder.models.entity;

import br.edu.ufscar.backend.mealsfinder.models.key.SavedPostId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "saved_posts")
public class SavedPost {

    @EmbeddedId
    private SavedPostId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    public SavedPost() {
    }

    public SavedPost(Client client, Post post) {
        this.client = client;
        this.post = post;
        this.id = new SavedPostId(client.getId(), post.getId());
    }

    public SavedPostId getId() {
        return id;
    }

    public void setId(SavedPostId id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedPost savedPost = (SavedPost) o;
        return Objects.equals(id, savedPost.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
