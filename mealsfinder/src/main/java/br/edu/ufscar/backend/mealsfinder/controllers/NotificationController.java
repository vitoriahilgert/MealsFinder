package br.edu.ufscar.backend.mealsfinder.controllers;

import br.edu.ufscar.backend.mealsfinder.dtos.notification.NotificationDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.Notification;
import br.edu.ufscar.backend.mealsfinder.services.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    private NotificationDTO convertToDto(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getAll() {
        List<Notification> notifications = notificationService.getAll();
        List<NotificationDTO> notificationDtos = notifications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationDtos);
    }

    @GetMapping("/clients/{clientId}/notifications")
    public ResponseEntity<List<NotificationDTO>> getClientNotifications(@PathVariable String clientId) {
        List<Notification> notifications = notificationService.getNotificationsForUser(clientId);
        List<NotificationDTO> notificationDtos = notifications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationDtos);
    }
}