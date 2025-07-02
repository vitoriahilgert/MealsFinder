package br.edu.ufscar.backend.mealsfinder.services.authentication.strategies;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("email")
public class EmailLoginStrategy implements ILoginStrategy {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(CredentialsDTO credentials) {
        System.out.println("Executando autenticação por e-mail.");

        User user = userRepository.findByEmail(credentials.getIdentifier())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com este e-mail."));

        if (Objects.equals(credentials.getPassword(), user.getPassword())) {
            System.out.println("Login bem-sucedido para: " + user.getUsername());
            return user;
        } else {
            throw new RuntimeException("Senha inválida.");
        }
    }
}

