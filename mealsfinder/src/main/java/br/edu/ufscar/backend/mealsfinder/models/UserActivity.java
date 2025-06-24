package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.ActionTypeEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.EntityTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.UUID;

@Entity(name = "user_activities")
class UserActivity {
    @Column(name = "id")
    private UUID id;

//    @ManyToOne
//    private User user;
//
//    @Column(nullable = false)
//    private UUID entityId;

    @Column(name = "action_type")
    private ActionTypeEnum actionType;

    @Column(name = "entity_type")
    private EntityTypeEnum entityType;

    @CreationTimestamp
    @Column(name = "timestamp")
    private int timestamp;

    @Column(name = "engagement_score")
    private float engagementScore;

    //private ObjectMapper context;


    public UserActivity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}