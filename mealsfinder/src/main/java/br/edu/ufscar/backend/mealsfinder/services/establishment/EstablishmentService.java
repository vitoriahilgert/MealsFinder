package br.edu.ufscar.backend.mealsfinder.services.establishment;

import br.edu.ufscar.backend.mealsfinder.dtos.establishment.EstablishmentUpdateDTO;
import br.edu.ufscar.backend.mealsfinder.events.EstablishmentUpdateEvent;
import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.repositories.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EstablishmentService(EstablishmentRepository establishmentRepository, ApplicationEventPublisher eventPublisher) {
        this.establishmentRepository = establishmentRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    public Establishment findById(String id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento com ID " + id + " não encontrado."));
    }

    @Transactional
    public Establishment updateEstablishmentProfile(String id, EstablishmentUpdateDTO updateDTO) {
        Establishment establishmentToUpdate = this.findById(id);

        establishmentToUpdate.setBio(updateDTO.getBio());

        Establishment updatedEstablishment = establishmentRepository.save(establishmentToUpdate);

        eventPublisher.publishEvent(new EstablishmentUpdateEvent(this, updatedEstablishment));

        return updatedEstablishment;
    }
}