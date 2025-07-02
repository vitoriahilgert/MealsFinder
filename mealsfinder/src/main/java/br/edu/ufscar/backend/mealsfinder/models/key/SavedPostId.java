package br.edu.ufscar.backend.mealsfinder.models.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SavedPostId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "post_id")
    private String postId;

    public SavedPostId() {
    }

    public SavedPostId(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedPostId that = (SavedPostId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
