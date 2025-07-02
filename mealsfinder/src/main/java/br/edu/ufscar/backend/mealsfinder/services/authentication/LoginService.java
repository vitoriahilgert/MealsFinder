package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.ILoginStrategy;
import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.LoginStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{

    private final LoginStrategyFactory loginStrategyFactory;

    public LoginService(LoginStrategyFactory loginStrategyFactory) {
        this.loginStrategyFactory = loginStrategyFactory;
    }

    @Override
    public User login(CredentialsDTO credentials) {
        ILoginStrategy strategy = loginStrategyFactory.getStrategy(credentials.getType().name());
        return strategy.login(credentials);
    }
}