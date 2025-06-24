package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.ImageType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private ImageType type;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;


    public Image() {
    }

    public Image(UUID id, String url, ImageType type, Establishment establishment) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.establishment = establishment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
