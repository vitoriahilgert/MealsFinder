package br.edu.ufscar.backend.mealsfinder.services.authentication.strategies;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("username")
public class UsernameLoginStrategy implements ILoginStrategy {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(CredentialsDTO credentials) {
        System.out.println("Executando autenticação por nome de usuário...");

        String username = credentials.getIdentifier();
        String rawPassword = credentials.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com este nome de usuário: " + username));

        if (Objects.equals(rawPassword, user.getPassword())) {
            System.out.println("Login com nome de usuário bem-sucedido para: " + user.getUsername());
            return user;
        } else {
            throw new RuntimeException("Senha inválida.");
        }
    }
}

