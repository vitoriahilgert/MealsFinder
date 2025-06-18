package br.edu.ufscar.backend.mealsfinder.controllers;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.User;
import br.edu.ufscar.backend.mealsfinder.services.authentication.AuthenticationService;
import br.edu.ufscar.backend.mealsfinder.services.authentication.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody CredentialsDTO credentials) {
        authenticationService.login(credentials);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/client")
    public ResponseEntity<User> registerClient(@RequestBody ClientRegisterDTO dto) {
        User user = authenticationService.registerClient(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register/establishment")
    public ResponseEntity<User> registerEstablishment(@RequestBody EstablishmentRegisterDTO dto) {
        User user = authenticationService.registerEstablishment(dto);
        return ResponseEntity.ok(user);
    }
}


