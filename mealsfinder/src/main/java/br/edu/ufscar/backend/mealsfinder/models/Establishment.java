package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.ImageType;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "establishments")
public class Establishment extends User {
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private EstablishmentTypesEnum type;

    @Column(name = "is_delivery")
    private boolean isDelivery;

    @Column(name = "is_in_person")
    private boolean isInPerson;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Embedded(name = "address")
    private Address address;

    @Column(name = "rejections")
    private short rejections;

//    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Image> images = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts = new ArrayList<>();

    public Establishment() {
    }

//    public List<Image> getMenuUrls() {
//        return this.images.stream()
//                .filter(image -> image.getType() == ImageType.MENU)
//                .collect(Collectors.toList());
//    }
//
//    public List<Image> getEstablishmentPicturesUrls() {
//        return this.images.stream()
//                .filter(image -> image.getType() == ImageType.ESTABLISHMENT_PICTURE)
//                .collect(Collectors.toList());
//    }

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

    public short getRejections() {
        return rejections;
    }

    public void setRejections(short rejections) {
        this.rejections = rejections;
    }

//    public List<Image> getImages() {
//        return images;
//    }
//
//    public void setImages(List<Image> images) {
//        this.images = images;
//    }
}
