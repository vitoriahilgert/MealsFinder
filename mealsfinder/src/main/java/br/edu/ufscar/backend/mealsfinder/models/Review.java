package br.edu.ufscar.backend.mealsfinder.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Review extends Post{
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private boolean isDelivery;

    @Column
    private Double establishmentRating;

    @Column
    private Double serviceRating;

    @Column
    private Double deliveryRating;

}
