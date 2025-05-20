package mealsfinder.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import mealsfinder.models.enums.ActionTypeEnum;
import mealsfinder.models.enums.EntityTypeEnum;

import java.util.UUID;

public class UserActivity {
    private UUID id;
    private UUID userId;
    private UUID entityId;
    private ActionTypeEnum actionType;
    private EntityTypeEnum entityType;
    private int timestamp;
    private float engagementScore;
    private ObjectMapper context;

    public UserActivity(UUID id, UUID userId, UUID entityId, ActionTypeEnum actionType, EntityTypeEnum entityType, int timestamp, float engagementScore, ObjectMapper context) {
        this.id = id;
        this.userId = userId;
        this.entityId = entityId;
        this.actionType = actionType;
        this.entityType = entityType;
        this.timestamp = timestamp;
        this.engagementScore = engagementScore;
        this.context = context;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public ActionTypeEnum getActionType() {
        return actionType;
    }

    public void setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
    }

    public EntityTypeEnum getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityTypeEnum entityType) {
        this.entityType = entityType;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public float getEngagementScore() {
        return engagementScore;
    }

    public void setEngagementScore(float engagementScore) {
        this.engagementScore = engagementScore;
    }

    public ObjectMapper getContext() {
        return context;
    }

    public void setContext(ObjectMapper context) {
        this.context = context;
    }
}