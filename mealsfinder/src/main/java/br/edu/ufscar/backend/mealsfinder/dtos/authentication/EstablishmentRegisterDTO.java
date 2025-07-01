package br.edu.ufscar.backend.mealsfinder.dtos.authentication;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentType;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;

import java.util.List;

public class EstablishmentRegisterDTO {
    private String cnpj;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String profilePicUrl;
    private String bio;
    private EstablishmentType type;
    private boolean isDelivery;
    private boolean isInPerson;
    private StatusEnum status;
    private String address;
    private List<String> menuUrls;
    private List<String> establishmentPicturesUrls;
    private int rejections;

    public EstablishmentRegisterDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public EstablishmentType getType() {
        return type;
    }

    public void setType(EstablishmentType type) {
        this.type = type;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public boolean isInPerson() {
        return isInPerson;
    }

    public void setInPerson(boolean inPerson) {
        isInPerson = inPerson;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getMenuUrls() {
        return menuUrls;
    }

    public void setMenuUrls(List<String> menuUrls) {
        this.menuUrls = menuUrls;
    }

    public List<String> getEstablishmentPicturesUrls() {
        return establishmentPicturesUrls;
    }

    public void setEstablishmentPicturesUrls(List<String> establishmentPicturesUrls) {
        this.establishmentPicturesUrls = establishmentPicturesUrls;
    }

    public int getRejections() {
        return rejections;
    }

    public void setRejections(int rejections) {
        this.rejections = rejections;
    }
}
