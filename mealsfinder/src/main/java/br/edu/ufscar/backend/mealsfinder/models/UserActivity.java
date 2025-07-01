package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.models.enums.ActivityType;
import br.edu.ufscar.backend.mealsfinder.models.enums.EntityType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_activities")
public class UserActivity {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false)
    private ActivityType actionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    private EntityType entityType;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "engagement_score")
    private Double engagementScore;

    @Column(name = "context_json", columnDefinition = "TEXT")
    private String contextJson;

    @PrePersist
    protected void onCreate() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }

    public UserActivity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActivityType getActionType() {
        return actionType;
    }

    public void setActionType(ActivityType actionType) {
        this.actionType = actionType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getEngagementScore() {
        return engagementScore;
    }

    public void setEngagementScore(Double engagementScore) {
        this.engagementScore = engagementScore;
    }

    public String getContextJson() {
        return contextJson;
    }

    public void setContextJson(String contextJson) {
        this.contextJson = contextJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivity that = (UserActivity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}