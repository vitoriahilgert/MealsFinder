package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.ActionTypeEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.EntityTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private UUID entityId;

    @Column(nullable = false)
    private ActionTypeEnum actionType;

    @Column(nullable = false)
    private EntityTypeEnum entityType;

    @CreationTimestamp
    private int timestamp;

    @Column(nullable = false)
    private float engagementScore;

    private ObjectMapper context;
}