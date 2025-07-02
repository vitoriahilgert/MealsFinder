package br.edu.ufscar.backend.mealsfinder.dtos.establishment;

import lombok.Getter;

@Getter
public class EstablishmentUpdateDTO {
    private final String bio;

    public EstablishmentUpdateDTO(String bio) {
        this.bio = bio;
    }
}
