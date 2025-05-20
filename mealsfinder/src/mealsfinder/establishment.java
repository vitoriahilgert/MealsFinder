package mealsfinder;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Establishment extends User {
    private String cnpj;
    private EstablishmentTypeEnum type;
    private boolean isDelivery;
    private boolean isInPerson;
    private StatusEnum status;
    private Address address;
    private List<String> menuUrls;
    private List<String> establishmentPicturesUrls;
    private int rejections;

    public Establishment() {
        super();
        this.menuUrls = new ArrayList<>();
        this.establishmentPicturesUrls = new ArrayList<>();
        this.status = StatusEnum.PENDING_APPROVAL;
        this.rejections = 0;
    }

    public Establishment(String email, String phoneNumber, String username, String password, 
                        String cnpj, EstablishmentTypeEnum type) {
        super(email, phoneNumber, username, password);
        this.cnpj = cnpj;
        this.type = type;
        this.menuUrls = new ArrayList<>();
        this.establishmentPicturesUrls = new ArrayList<>();
        this.status = StatusEnum.PENDING_APPROVAL;
        this.rejections = 0;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EstablishmentTypeEnum getType() {
        return type;
    }

    public void setType(EstablishmentTypeEnum type) {
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

    public List<String> getMenuUrls() {
        return menuUrls;
    }

    public void setMenuUrls(List<String> menuUrls) {
        this.menuUrls = menuUrls;
    }
    
    public void addMenuUrl(String url) {
        if (!menuUrls.contains(url)) {
            menuUrls.add(url);
        }
    }
    
    public void removeMenuUrl(String url) {
        menuUrls.remove(url);
    }

    public List<String> getEstablishmentPicturesUrls() {
        return establishmentPicturesUrls;
    }

    public void setEstablishmentPicturesUrls(List<String> establishmentPicturesUrls) {
        this.establishmentPicturesUrls = establishmentPicturesUrls;
    }
    
    public void addPictureUrl(String url) {
        if (!establishmentPicturesUrls.contains(url)) {
            establishmentPicturesUrls.add(url);
        }
    }
    
    public void removePictureUrl(String url) {
        establishmentPicturesUrls.remove(url);
    }

    public int getRejections() {
        return rejections;
    }

    public void setRejections(int rejections) {
        this.rejections = rejections;
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