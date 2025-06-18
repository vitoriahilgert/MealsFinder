package br.edu.ufscar.backend.mealsfinder.services.authentication.strategies;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import org.springframework.stereotype.Service;

@Service("email")
public class EmailLoginStrategy implements ILoginStrategy {
    @Override
    public void login(CredentialsDTO credentials) {
        System.out.println("Autenticação por e-mail");
    }
}

