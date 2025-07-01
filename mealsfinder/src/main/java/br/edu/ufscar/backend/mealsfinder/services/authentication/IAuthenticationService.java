package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;

public interface IAuthenticationService {
    User registerClient(ClientRegisterDTO clientRegisterDTO);

    User registerEstablishment(EstablishmentRegisterDTO establishmentRegisterDTO);

    void login(CredentialsDTO credentials);
}

