package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flight {
    private final SimpleStringProperty flightCode;
    private final SimpleStringProperty flightName;
    private final SimpleStringProperty source;
    private final SimpleStringProperty destination;
    private final SimpleIntegerProperty capacity;
    private final SimpleStringProperty dateOfJourney;
    public Flight(String flightCode, String flightName, String source, String destination, int capacity, String dateOfJourney) {
        this.flightCode = new SimpleStringProperty(flightCode);
        this.flightName = new SimpleStringProperty(flightName);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.dateOfJourney = new SimpleStringProperty(dateOfJourney);
    }
    public String getFlightCode() {
        return flightCode.get();
    }
    public String getFlightName() {
        return flightName.get();
    }
    public String getSource() {
        return source.get();
    }
    public String getDestination() {
        return destination.get();
    }
    public int getCapacity() {
        return capacity.get();
    }
    public String getDateOfJourney() {
        return dateOfJourney.get();
    }
    public SimpleStringProperty flightCodeProperty() {
        return flightCode;
    }
    public SimpleStringProperty flightNameProperty() {
        return flightName;
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }
    public SimpleStringProperty destinationProperty() {
        return destination;
    }
    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }
    public SimpleStringProperty dateOfJourneyProperty() {
        return dateOfJourney;
    }
}