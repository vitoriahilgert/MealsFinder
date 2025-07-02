package br.edu.ufscar.backend.mealsfinder.dtos.establishment;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentType;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import br.edu.ufscar.backend.mealsfinder.models.states.EstablishmentState;

import java.time.LocalDateTime;

public class EstablishmentResponseDTO {
    private String id;
    private String cnpj;
    private EstablishmentType type;
    private String name;
    private String bio;
    private StatusEnum status;
    private String profilePicureUrl;
    private LocalDateTime accountCreationDate;
    private boolean isVisible;

    public EstablishmentResponseDTO() {
    }

    public EstablishmentResponseDTO(Establishment establishment) {
        this.id = establishment.getId();
        this.name = establishment.getName();
        this.bio = establishment.getBio();
        this.status = establishment.getStatus();
        this.profilePicureUrl = establishment.getProfilePictureUrl();
        this.accountCreationDate = establishment.getAccountCreationDate();
        this.profilePicureUrl = establishment.getProfilePictureUrl();
        this.cnpj = establishment.getCnpj();
        this.type = establishment.getEstablishmentType();
        this.isVisible = establishment.isVisible();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EstablishmentType getType() {
        return type;
    }

    public void setType(EstablishmentType type) {
        this.type = type;
    }

    public String getProfilePicureUrl() {
        return profilePicureUrl;
    }

    public void setProfilePicureUrl(String profilePicureUrl) {
        this.profilePicureUrl = profilePicureUrl;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
