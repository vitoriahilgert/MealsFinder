package br.edu.ufscar.backend.mealsfinder.services.authentication.strategies;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("phone_number")
public class PhoneNumberLoginStrategy implements ILoginStrategy{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(CredentialsDTO credentials) {
        System.out.println("Executando autenticação por número de telefone...");

        String phoneNumber = credentials.getIdentifier();
        String rawPassword = credentials.getPassword();

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com este número de telefone: " + phoneNumber));

        if (Objects.equals(rawPassword, user.getPassword())) {
            System.out.println("Login com número de telefone bem-sucedido para: " + user.getUsername());
            return user;
        } else {
            throw new RuntimeException("Senha inválida.");
        }
    }
}

