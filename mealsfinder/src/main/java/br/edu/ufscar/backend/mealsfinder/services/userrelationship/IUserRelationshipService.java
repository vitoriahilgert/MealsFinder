package br.edu.ufscar.backend.mealsfinder.services.userrelationship;

import java.util.List;
import java.util.UUID;

public interface IUserRelationshipService {
    void followUser(UUID followerId, UUID followedUserId);
    void unfollowUser(UUID followerId, UUID followedUserId);
    List<UUID> getFollowers(UUID userId);
    List<UUID> getFollowing(UUID userId);
    boolean isFollowing(UUID followerId, UUID followedUserId);

    void blockUser(UUID blockerId, UUID blockedUserId);
    void unblockUser(UUID blockerId, UUID blockedUserId);
    List<UUID> getBlockedUsers(UUID userId);
    boolean isBlocked(UUID blockerId, UUID blockedUserId);
}
