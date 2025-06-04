package br.edu.ufscar.backend.mealsfinder.services.contentrelationship;

import java.util.List;
import java.util.UUID;

public interface IContentRelationshipService {
    void addCommentToPost(UUID postId, UUID commentId);
    void removeCommentFromPost(UUID postId, UUID commentId);
    List<UUID> getCommentsByPost(UUID postId);
    UUID getPostByComment(UUID commentId);

    void likeContent(UUID contentId, UUID userId);
    void unlikeContent(UUID contentId, UUID userId);
    List<UUID> getContentLikes(UUID contentId);
    boolean isContentLikedByUser(UUID contentId, UUID userId);
    int getContentLikeCount(UUID contentId);

    void savePost(UUID userId, UUID postId);
    void unsavePost(UUID userId, UUID postId);
    List<UUID> getSavedPosts(UUID userId);
    boolean isPostSavedByUser(UUID userId, UUID postId);
    List<UUID> getReviewsByUser(UUID userID);
}
