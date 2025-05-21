package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.User;
import br.edu.ufscar.backend.mealsfinder.models.UserFactory;
import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.ILoginStrategy;
import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.LoginStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService implements IAuthenticationService, ILoginStrategy {
    @Autowired
    private LoginStrategyFactory loginStrategyFactory;

    @Override
    public void login(CredentialsDTO credentials) {
        ILoginStrategy strategy = loginStrategyFactory.getStrategy(credentials.getType().toString());
        strategy.login(credentials);
    }

    @Override
    public User registerClient(ClientRegisterDTO clientRegisterDTO) {
        return UserFactory.createClient(clientRegisterDTO);
    }

    @Override
    public User registerEstablishment(EstablishmentRegisterDTO establishmentRegisterDTO) {
        return UserFactory.createEstablishment(establishmentRegisterDTO);
    }

}