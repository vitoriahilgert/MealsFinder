package br.edu.ufscar.backend.mealsfinder.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Comment extends Content {
    private UUID postId;

    public Comment() {
        super();
    }

    public Comment(UUID creatorId, String text, UUID postId) {
        super(creatorId, text);
        this.postId = postId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    @Override
    public String getContentType() {
        return "COMMENT";
    }
    
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + getId() +
                ", creator=" + getCreatorId() +
                ", post=" + postId +
                ", likes=" + getLikeCount() +
                '}';
    }
}
