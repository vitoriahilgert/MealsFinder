package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Establishment extends User {
    private String cnpj;
    private EstablishmentTypesEnum type;
    private boolean isDelivery;
    private boolean isInPerson;
    private StatusEnum status;
    private Address address;
    private List<String> menuUrls;
    private List<String> establishmentPicturesUrls;
    private int rejections;

    public Establishment() {
    }

    public Establishment(UUID id, String email, String phoneNumber, String username, String password, String profilePicUrl, boolean isAccountConfirmed, String confirmationCode, String bio, List<UUID> followers, String cnpj, EstablishmentTypesEnum type, boolean isDelivery, boolean isInPerson, StatusEnum status, Address address, List<String> menuUrls, List<String> establishmentPicturesUrls, int rejections) {
        super(id, email, phoneNumber, username, password, profilePicUrl, isAccountConfirmed, confirmationCode, bio, followers);
        this.cnpj = cnpj;
        this.type = type;
        this.isDelivery = isDelivery;
        this.isInPerson = isInPerson;
        this.status = status;
        this.address = address;
        this.menuUrls = menuUrls;
        this.establishmentPicturesUrls = establishmentPicturesUrls;
        this.rejections = rejections;
    }

    public Establishment(String cnpj, EstablishmentTypesEnum type, boolean isDelivery, boolean isInPerson, StatusEnum status, Address address, List<String> menuUrls, List<String> establishmentPicturesUrls, int rejections) {
        this.cnpj = cnpj;
        this.type = type;
        this.isDelivery = isDelivery;
        this.isInPerson = isInPerson;
        this.status = status;
        this.address = address;
        this.menuUrls = menuUrls;
        this.establishmentPicturesUrls = establishmentPicturesUrls;
        this.rejections = rejections;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EstablishmentTypesEnum getType() {
        return type;
    }

    public void setType(EstablishmentTypesEnum type) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public void addMenuUrl(String url) {
        if (!menuUrls.contains(url)) {
            menuUrls.add(url);
        }
    }
    
    public void addPictureUrl(String url) {
        if (!establishmentPicturesUrls.contains(url)) {
            establishmentPicturesUrls.add(url);
        }
    }
    
    public void incrementRejections() {
        this.rejections++;
        if (this.rejections > 3) {
            this.status = StatusEnum.REJECTED;
        }
    }

    @Override
    public String getUserType() {
        return "ESTABLISHMENT";
    }
    
    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
