package br.edu.ufscar.backend.mealsfinder.controllers;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.CredentialsDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.User;
import br.edu.ufscar.backend.mealsfinder.services.authentication.LoginService;
import br.edu.ufscar.backend.mealsfinder.services.authentication.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final LoginService loginService;
    private final RegistrationService registrationService;

    public AuthenticationController(LoginService loginService, RegistrationService registrationService) {
        this.loginService = loginService;
        this.registrationService = registrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody CredentialsDTO credentials) {
        User authenticatedUser = loginService.login(credentials);
        return ResponseEntity.ok(authenticatedUser);
    }

    @PostMapping("/register/client")
    public ResponseEntity<User> registerClient(@RequestBody ClientRegisterDTO dto) {
        User user = registrationService.registerClient(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register/establishment")
    public ResponseEntity<User> registerEstablishment(@RequestBody EstablishmentRegisterDTO dto) {
        User user = registrationService.registerEstablishment(dto);
        return ResponseEntity.ok(user);
    }
}


