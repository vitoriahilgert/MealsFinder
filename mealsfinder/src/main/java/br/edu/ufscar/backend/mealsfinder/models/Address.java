package br.edu.ufscar.backend.mealsfinder.models;

public class Address {
    private String CEP;
    private String city;
    private String state;
    private String street;
    private String number;
    private String neighborhood;
    private String country;

    public Address() {
    }

    public Address(String CEP, String city, String state, String street, String number, String neighborhood, String country) {
        this.CEP = CEP;
        this.city = city;
        this.state = state;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.country = country;
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

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
