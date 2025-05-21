package br.edu.ufscar.backend.mealsfinder.models;

import java.util.UUID;

public class UserFactory {
    // TODO: trocar parametros para DTOs

    public static User createClient(UUID id) {
        return new Client();
    }

    public static User createEstablishment(UUID id) {
        return new Establishment();
    }
}