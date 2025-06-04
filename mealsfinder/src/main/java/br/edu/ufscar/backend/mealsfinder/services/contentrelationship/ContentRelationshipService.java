package br.edu.ufscar.backend.mealsfinder.services.contentrelationship;

import java.util.*;

public class ContentRelationshipService implements IContentRelationshipService {
    private final Map<UUID, Set<UUID>> postCommentsMap = new HashMap<>(); // postId -> commentIds
    private final Map<UUID, UUID> commentPostMap = new HashMap<>(); // commentId -> postId
    private final Map<UUID, Set<UUID>> contentLikesMap = new HashMap<>(); // contentId -> userIds who liked
    private final Map<UUID, Set<UUID>> userSavedPostsMap = new HashMap<>(); // userId -> savedPostIds

    @Override
    public void addCommentToPost(UUID postId, UUID commentId) {
        postCommentsMap.computeIfAbsent(postId, k -> new HashSet<>()).add(commentId);
        commentPostMap.put(commentId, postId);
    }

    @Override
    public void removeCommentFromPost(UUID postId, UUID commentId) {
        Set<UUID> postComments = postCommentsMap.get(postId);
        if (postComments != null) {
            postComments.remove(commentId);
        }
        commentPostMap.remove(commentId);

        // Also remove any likes for this comment
        contentLikesMap.remove(commentId);
    }

    @Override
    public List<UUID> getCommentsByPost(UUID postId) {
        return new ArrayList<>(postCommentsMap.getOrDefault(postId, new HashSet<>()));
    }

    @Override
    public UUID getPostByComment(UUID commentId) {
        return commentPostMap.get(commentId);
    }

    @Override
    public void likeContent(UUID contentId, UUID userId) {
        contentLikesMap.computeIfAbsent(contentId, k -> new HashSet<>()).add(userId);
    }

    @Override
    public void unlikeContent(UUID contentId, UUID userId) {
        Set<UUID> likes = contentLikesMap.get(contentId);
        if (likes != null) {
            likes.remove(userId);
        }
    }

    @Override
    public List<UUID> getContentLikes(UUID contentId) {
        return new ArrayList<>(contentLikesMap.getOrDefault(contentId, new HashSet<>()));
    }

    @Override
    public boolean isContentLikedByUser(UUID contentId, UUID userId) {
        Set<UUID> likes = contentLikesMap.get(contentId);
        return likes != null && likes.contains(userId);
    }

    @Override
    public int getContentLikeCount(UUID contentId) {
        Set<UUID> likes = contentLikesMap.get(contentId);
        return likes != null ? likes.size() : 0;
    }

    @Override
    public void savePost(UUID userId, UUID postId) {
        userSavedPostsMap.computeIfAbsent(userId, k -> new HashSet<>()).add(postId);
    }

    @Override
    public void unsavePost(UUID userId, UUID postId) {
        Set<UUID> savedPosts = userSavedPostsMap.get(userId);
        if (savedPosts != null) {
            savedPosts.remove(postId);
        }
    }

    @Override
    public List<UUID> getSavedPosts(UUID userId) {
        return new ArrayList<>(userSavedPostsMap.getOrDefault(userId, new HashSet<>()));
    }

    @Override
    public boolean isPostSavedByUser(UUID userId, UUID postId) {
        Set<UUID> savedPosts = userSavedPostsMap.get(userId);
        return savedPosts != null && savedPosts.contains(postId);
    }

    public List<UUID> getReviewsByUser(UUID userID) {
        return null;
    }
}
