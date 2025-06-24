package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity(tableName = "user_activity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserActivity {
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private User user;

    @Column(name = "entity_id")
    private UUID entityId;

//    @Column(nullable = false)
//    private ActionTypeEnum actionType;
//
//    @Column(nullable = false)
//    private EntityTypeEnum entityType;

    @Column(name = "timestamp")
    private int timestamp;

    @Column(name = "engagement_score")
    private float engagementScore;

    // private ObjectMapper context;
}