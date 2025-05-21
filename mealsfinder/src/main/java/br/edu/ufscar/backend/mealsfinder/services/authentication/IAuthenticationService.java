package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.models.User;

import java.util.UUID;

public interface IAuthenticationService {
    User registerClient(UUID id); //TODO: talvez fazer duas factories

    User registerEstablishment(UUID id);
}