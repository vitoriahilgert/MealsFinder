package br.edu.ufscar.backend.mealsfinder.services;

import br.edu.ufscar.backend.mealsfinder.models.User;

import java.util.UUID;

public interface AuthenticationService {
    User registerClient(UUID id);

    User registerEstablishment(UUID id);
}