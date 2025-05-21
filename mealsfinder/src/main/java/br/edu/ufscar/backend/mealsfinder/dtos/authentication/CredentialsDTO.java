package br.edu.ufscar.backend.mealsfinder.dtos.authentication;

import br.edu.ufscar.backend.mealsfinder.models.enums.LoginTypeEnum;

import java.util.Optional;

public class CredentialsDTO {
    private String identifier;
    private LoginTypeEnum type;
    private String password;

    public CredentialsDTO() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LoginTypeEnum getType() {
        return type;
    }

    public void setType(LoginTypeEnum type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
