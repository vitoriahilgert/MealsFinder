package br.edu.ufscar.backend.mealsfinder.dtos.notification;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationDTO {
    private UUID id;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
}