package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Passenger {
    private SimpleIntegerProperty id;
    private SimpleStringProperty username;
    private SimpleStringProperty flightCode;
    private SimpleStringProperty passengerNumber;
    private SimpleStringProperty passportNumber;
    private SimpleStringProperty nationality;

    public Passenger(int id, String username, String flightCode, String passengerNumber, String passportNumber, String nationality) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.flightCode = new SimpleStringProperty(flightCode);
        this.passengerNumber = new SimpleStringProperty(passengerNumber);
        this.passportNumber = new SimpleStringProperty(passportNumber);
        this.nationality = new SimpleStringProperty(nationality);
    }

    public int getId() {
        return id.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getFlightCode() {
        return flightCode.get();
    }

    public String getPassengerNumber() {
        return passengerNumber.get();
    }

    public String getPassportNumber() {
        return passportNumber.get();
    }

    public String getNationality() {
        return nationality.get();
    }
}
