package br.edu.ufscar.backend.mealsfinder.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class Address {
    @Column(name = "cep")
    private String cep;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "country")
    private String country;

    public Address() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
