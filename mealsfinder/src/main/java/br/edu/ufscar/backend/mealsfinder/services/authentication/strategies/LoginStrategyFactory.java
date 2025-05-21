package br.edu.ufscar.backend.mealsfinder.services.authentication.strategies;

import br.edu.ufscar.backend.mealsfinder.services.authentication.strategies.ILoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginStrategyFactory {

    private final Map<String, ILoginStrategy> strategies;

    @Autowired
    public LoginStrategyFactory(Map<String, ILoginStrategy> strategies) {
        this.strategies = strategies;
    }

    public ILoginStrategy getStrategy(String method) {
        ILoginStrategy strategy = strategies.get(method);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown login method: " + method);
        }
        return strategy;
    }
}