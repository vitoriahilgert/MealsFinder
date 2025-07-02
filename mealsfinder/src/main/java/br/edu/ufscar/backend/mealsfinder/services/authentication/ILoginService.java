package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;

public interface ILoginService {
    User login(CredentialsDTO credentials);
}
