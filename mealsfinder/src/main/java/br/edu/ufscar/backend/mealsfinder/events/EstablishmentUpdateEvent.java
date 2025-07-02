package br.edu.ufscar.backend.mealsfinder.events;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EstablishmentUpdateEvent extends ApplicationEvent {

    private final Establishment establishment;

    public EstablishmentUpdateEvent(Object source, Establishment establishment) {
        super(source);
        this.establishment = establishment;
    }

}