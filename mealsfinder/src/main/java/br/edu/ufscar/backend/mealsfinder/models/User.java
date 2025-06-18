package br.edu.ufscar.backend.mealsfinder.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String phoneNumber;

    @Column(unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column
    private String profilePicUrl;

    @Column
    private boolean isAccountConfirmed;

    @Column
    private String confirmationCode;

    @Column
    private String bio;

    @OneToMany(mappedBy = "user")
    private List<Client> followers = new ArrayList<>();

}
