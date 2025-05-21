package br.edu.ufscar.backend.mealsfinder.services;

import br.edu.ufscar.backend.mealsfinder.models.User;
import br.edu.ufscar.backend.mealsfinder.models.UserFactory;

import java.util.UUID;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User registerClient(UUID id) {
        return UserFactory.createClient(id);
    }

    @Override
    public User registerEstablishment(UUID id) {
        return UserFactory.createEstablishment(id);
    }
}