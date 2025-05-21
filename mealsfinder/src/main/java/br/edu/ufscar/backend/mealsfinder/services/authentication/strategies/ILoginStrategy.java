package br.edu.ufscar.backend.mealsfinder.services.authentication.strategies;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.services.authentication.IAuthenticationService;

public interface ILoginStrategy {
    public void login(CredentialsDTO credentials);
}
