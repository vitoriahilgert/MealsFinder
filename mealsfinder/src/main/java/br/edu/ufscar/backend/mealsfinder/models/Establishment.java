package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Establishment extends User {
    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private EstablishmentTypesEnum type;

    @Column(nullable = false)
    private boolean isDelivery;

    @Column(nullable = false)
    private boolean isInPerson;

    @Column(nullable = false)
    private StatusEnum status;


    private Address address;
    private List<String> menuUrls;
    private List<String> establishmentPicturesUrls;
    private int rejections;
}
