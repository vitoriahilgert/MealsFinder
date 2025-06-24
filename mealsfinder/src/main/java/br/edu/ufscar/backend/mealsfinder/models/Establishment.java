package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import br.edu.ufscar.backend.mealsfinder.models.enums.EstablishmentTypesEnum;
import br.edu.ufscar.backend.mealsfinder.models.enums.ImageType;
import br.edu.ufscar.backend.mealsfinder.models.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(tableName = "establishments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Establishment extends User {

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "establishment_type")
    private EstablishmentTypesEnum establishmentType;

    @Column(name = "is_delivery")
    private boolean delivery = false;  // Changed field name to match setter

    @Column(name = "is_in_person")
    private boolean inPerson = true;   // Changed field name to match setter

    @Column(name = "status")
    private StatusEnum status = StatusEnum.PENDING;

    // Address embedded fields - you'll need to flatten these
    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "cep")
    private String cep;

    @Column(name = "country")
    private String country;

    @Column(name = "rejections")
    private int rejections = 0;

    @Collection(targetEntity = Image.class, columnName = "image_ids")
    private Set<Image> images = new HashSet<>();

    @Collection(targetEntity = Review.class, columnName = "received_review_ids")
    private Set<Review> receivedReviews = new HashSet<>();

    public Establishment(String email, String username, String password, String cnpj, EstablishmentTypesEnum establishmentType) {
        super(email, username, password);
        this.cnpj = cnpj;
        this.establishmentType = establishmentType;
    }

    @Override
    public String getUserType() {
        return "ESTABLISHMENT";
    }

    public Set<Image> getMenuImages() {
        return this.images.stream()
                .filter(image -> image.getType() == ImageType.MENU)
                .collect(Collectors.toSet());
    }

    public Set<Image> getEstablishmentImages() {
        return this.images.stream()
                .filter(image -> image.getType() == ImageType.ESTABLISHMENT_PICTURE)
                .collect(Collectors.toSet());
    }

    public boolean addImage(Image image) {
        image.setEstablishment(this);
        return this.images.add(image);
    }

    public boolean removeImage(Image image) {
        boolean removed = this.images.remove(image);
        if (removed) {
            image.setEstablishment(null);
        }
        return removed;
    }

    public double getAverageRating() {
        return this.receivedReviews.stream()
                .mapToDouble(Review::getOverallDetailedRating)
                .average()
                .orElse(0.0);
    }

    public int getReviewCount() {
        return this.receivedReviews.size();
    }

    public boolean isApproved() {
        return this.status == StatusEnum.APPROVED;
    }

    public boolean isPending() {
        return this.status == StatusEnum.PENDING;
    }

    public boolean isRejected() {
        return this.status == StatusEnum.REJECTED;
    }

    public void approve() {
        this.status = StatusEnum.APPROVED;
    }

    public void reject() {
        this.status = StatusEnum.REJECTED;
        this.rejections++;
    }

    public void resetToPending() {
        this.status = StatusEnum.PENDING;
    }

    public boolean supportsDelivery() {
        return this.delivery;  // Updated to match field name
    }

    public boolean supportsInPerson() {
        return this.inPerson;  // Updated to match field name
    }

    // Helper methods for Address (since we flattened the embedded object)
    public Address getAddress() {
        Address address = new Address();
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setNeighborhood(this.neighborhood);
        address.setCity(this.city);
        address.setState(this.state);
        address.setCep(this.cep);
        address.setCountry(this.country);
        return address;
    }

    public void setAddress(Address address) {
        if (address != null) {
            this.street = address.getStreet();
            this.number = address.getNumber();
            this.neighborhood = address.getNeighborhood();
            this.city = address.getCity();
            this.state = address.getState();
            this.cep = address.getCep();
            this.country = address.getCountry();
        }
    }
}