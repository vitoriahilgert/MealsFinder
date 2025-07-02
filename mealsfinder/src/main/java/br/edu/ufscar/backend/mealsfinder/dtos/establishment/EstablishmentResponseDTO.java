package br.edu.ufscar.backend.mealsfinder.dtos.establishment;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import br.edu.ufscar.backend.mealsfinder.models.states.EstablishmentState;

public class EstablishmentResponseDTO {
    private String id;
    private String name;
    private String bio;
    private StatusEnum status;
    private String state;

    public EstablishmentResponseDTO() {
    }

    public EstablishmentResponseDTO(Establishment establishment) {
        this.id = establishment.getId();
        this.name = establishment.getName();
        this.bio = establishment.getBio();
        this.status = establishment.getStatus();
        this.state = establishment.getState().getClass().getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
