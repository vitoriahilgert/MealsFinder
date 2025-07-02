package br.edu.ufscar.backend.mealsfinder.listeners;

import br.edu.ufscar.backend.mealsfinder.events.EstablishmentUpdateEvent;
import br.edu.ufscar.backend.mealsfinder.models.entity.Client;
import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.entity.Follow;
import br.edu.ufscar.backend.mealsfinder.models.entity.Notification;
import br.edu.ufscar.backend.mealsfinder.repositories.FollowRepository;
import br.edu.ufscar.backend.mealsfinder.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class NotificationListener {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private FollowRepository followRepository;

    @Async
    @EventListener
    @Transactional
    public void handleEstablishmentUpdate(EstablishmentUpdateEvent event) {
        Establishment establishment = event.getEstablishment();
        List<Follow> followRelationships = followRepository.findByFollowingId(establishment.getId());
        if (followRelationships == null || followRelationships.isEmpty()) {
            return;
        }

        String message = String.format("O restaurante %s que você segue fez uma atualização no perfil.", establishment.getName());

        for (Follow follow : followRelationships) {
            Client followerClient = follow.getFollower();

            Notification notification = new Notification(followerClient, message);
            notificationRepository.save(notification);
        }

    }
}