package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.Client;
import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;

import java.util.UUID;

public class UserFactory {
    public static User createClient(ClientRegisterDTO clientDTO) {

        return new Client();
    }

    public static User createEstablishment(EstablishmentRegisterDTO establishmentDTO) {

        return new Establishment();
    }
}

