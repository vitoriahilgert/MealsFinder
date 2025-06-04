package br.edu.ufscar.backend.mealsfinder.services.userrelationship;

import java.util.*;

public class UserRelationshipService implements IUserRelationshipService{
    private final Map<UUID, Set<UUID>> followersMap = new HashMap<>();
    private final Map<UUID, Set<UUID>> followingMap = new HashMap<>();
    private final Map<UUID, Set<UUID>> blockedUsersMap = new HashMap<>();
    private final Map<UUID, Set<UUID>> savedPostsMap = new HashMap<>();

    @Override
    public void followUser(UUID followerId, UUID followedUserId) {
        if (followerId.equals(followedUserId)) {
            throw new IllegalArgumentException("User cannot follow themselves");
        }
        if (isBlocked(followedUserId, followerId)) {
            throw new IllegalStateException("Cannot follow a user who has blocked you");
        }

        followingMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followedUserId);
        followersMap.computeIfAbsent(followedUserId, k -> new HashSet<>()).add(followerId);
    }

    @Override
    public void unfollowUser(UUID followerId, UUID followedUserId) {
        Set<UUID> following = followingMap.get(followerId);
        if (following != null) {
            following.remove(followedUserId);
        }

        Set<UUID> followers = followersMap.get(followedUserId);
        if (followers != null) {
            followers.remove(followerId);
        }
    }

    @Override
    public List<UUID> getFollowers(UUID userId) {
        return new ArrayList<>(followersMap.getOrDefault(userId, new HashSet<>()));
    }

    @Override
    public List<UUID> getFollowing(UUID userId) {
        return new ArrayList<>(followingMap.getOrDefault(userId, new HashSet<>()));
    }

    @Override
    public boolean isFollowing(UUID followerId, UUID followedUserId) {
        Set<UUID> following = followingMap.get(followerId);
        return following != null && following.contains(followedUserId);
    }

    @Override
    public void blockUser(UUID blockerId, UUID blockedUserId) {
        if (blockerId.equals(blockedUserId)) {
            throw new IllegalArgumentException("User cannot block themselves");
        }

        // Remove any existing follow relationships
        unfollowUser(blockerId, blockedUserId);
        unfollowUser(blockedUserId, blockerId);

        blockedUsersMap.computeIfAbsent(blockerId, k -> new HashSet<>()).add(blockedUserId);
    }

    @Override
    public void unblockUser(UUID blockerId, UUID blockedUserId) {
        Set<UUID> blocked = blockedUsersMap.get(blockerId);
        if (blocked != null) {
            blocked.remove(blockedUserId);
        }
    }

    @Override
    public List<UUID> getBlockedUsers(UUID userId) {
        return new ArrayList<>(blockedUsersMap.getOrDefault(userId, new HashSet<>()));
    }

    @Override
    public boolean isBlocked(UUID blockerId, UUID blockedUserId) {
        Set<UUID> blocked = blockedUsersMap.get(blockerId);
        return blocked != null && blocked.contains(blockedUserId);
    }


}
