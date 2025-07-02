package br.edu.ufscar.backend.mealsfinder.models.entity;

import br.edu.ufscar.backend.mealsfinder.models.key.PostLikeId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "post_likes")
public class PostLike {

    @EmbeddedId
    private PostLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    public PostLike() {
    }

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
        this.id = new PostLikeId(user.getId(), post.getId());
    }

    public PostLikeId getId() {
        return id;
    }

    public void setId(PostLikeId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        PostLike postLike = (PostLike) o;
        return Objects.equals(id, postLike.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
