package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.UserFactory;
import br.edu.ufscar.backend.mealsfinder.models.entity.Client;
import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.repositories.UserRepository;
import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.ILoginStrategy;
import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.LoginStrategyFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private LoginStrategyFactory loginStrategyFactory;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(CredentialsDTO credentials) {
        ILoginStrategy strategy = loginStrategyFactory.getStrategy(credentials.getType().toString());
        return strategy.login(credentials);
    }

    @Override
    @Transactional
    public User registerClient(ClientRegisterDTO clientRegisterDTO) {
        Client newClient = userFactory.createClient(clientRegisterDTO);
        return userRepository.save(newClient);
    }

    @Override
    @Transactional
    public User registerEstablishment(EstablishmentRegisterDTO establishmentRegisterDTO) {
        Establishment newEstablishment = userFactory.createEstablishment(establishmentRegisterDTO);
        return userRepository.save(newEstablishment);
    }

}

