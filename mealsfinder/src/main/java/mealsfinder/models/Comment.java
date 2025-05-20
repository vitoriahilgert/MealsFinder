package mealsfinder.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment extends Content {
    private UUID postId;
    private UUID parentCommentId;
    private List<UUID> childCommentIds;

    public Comment() {
        super();
        this.childCommentIds = new ArrayList<>();
    }

    public Comment(UUID creatorId, String text, UUID postId) {
        super(creatorId, text);
        this.postId = postId;
        this.parentCommentId = null;
        this.childCommentIds = new ArrayList<>();
    }

    public Comment(UUID creatorId, String text, UUID postId, UUID parentCommentId) {
        super(creatorId, text);
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.childCommentIds = new ArrayList<>();
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public UUID getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(UUID parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public List<UUID> getChildCommentIds() {
        return childCommentIds;
    }

    public void setChildCommentIds(List<UUID> childCommentIds) {
        this.childCommentIds = childCommentIds;
    }

    public void addChildComment(UUID commentId) {
        if (commentId != null && !childCommentIds.contains(commentId)) {
            childCommentIds.add(commentId);
        }
    }

    public boolean removeChildComment(UUID commentId) {
        return childCommentIds.remove(commentId);
    }

    public int getReplyCount() {
        return childCommentIds.size();
    }

    public boolean isTopLevelComment() {
        return parentCommentId == null;
    }

    public boolean hasReplies() {
        return !childCommentIds.isEmpty();
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
                ", parent=" + (parentCommentId != null ? parentCommentId : "none") +
                ", replies=" + getReplyCount() +
                ", likes=" + getLikeCount() +
                '}';
    }
}