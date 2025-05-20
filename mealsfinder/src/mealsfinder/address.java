package mealsfinder;

public class Address {
    private String CEP;
    private String city;
    private String state;
    private String street;
    private String number;
    private String neighborhood;
    private String country;

    private String formattedAddress;
    private String placeId; // Google Maps

    public Address() {
    }

    public Address(String street, String number, String city, String state, String CEP) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.CEP = CEP;
        this.country = "Brasil";
        updateFormattedAddress();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        updateFormattedAddress();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        updateFormattedAddress();
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        updateFormattedAddress();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        updateFormattedAddress();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        updateFormattedAddress();
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
        updateFormattedAddress();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        updateFormattedAddress();
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    private void updateFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        
        // Add street
        if (street != null && !street.isEmpty()) {
            sb.append(street);
        }
        
        // Add number
        if (number != null && !number.isEmpty()) {
            sb.append(", ").append(number);
        }
        
        // Add neighborhood
        if (neighborhood != null && !neighborhood.isEmpty()) {
            sb.append(", ").append(neighborhood);
        }
        
        // Add city
        if (city != null && !city.isEmpty()) {
            sb.append(", ").append(city);
        }
        
        // Add state and postal code
        if (state != null && !state.isEmpty() && CEP != null && !CEP.isEmpty()) {
            sb.append(", ").append(state).append(" ").append(CEP);
        } else if (state != null && !state.isEmpty()) {
            sb.append(", ").append(state);
        } else if (CEP != null && !CEP.isEmpty()) {
            sb.append(", ").append(CEP);
        }
        
        // Add country
        if (country != null && !country.isEmpty()) {
            sb.append(", ").append(country);
        }
        
        this.formattedAddress = sb.toString();
    }

    @Override
    public String toString() {
        return formattedAddress;
    }
}