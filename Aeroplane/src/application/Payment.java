package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Payment {
    private final SimpleStringProperty username;
    private final SimpleStringProperty flightCode;
    private final SimpleStringProperty cardNumber;
    private final SimpleDoubleProperty amount;
    private final SimpleStringProperty paymentDate;

    public Payment(String username, String flightCode, String cardNumber, double amount, String paymentDate) {
        this.username = new SimpleStringProperty(username);
        this.flightCode = new SimpleStringProperty(flightCode);
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.amount = new SimpleDoubleProperty(amount);
        this.paymentDate = new SimpleStringProperty(paymentDate);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getFlightCode() {
        return flightCode.get();
    }

    public SimpleStringProperty flightCodeProperty() {
        return flightCode;
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate.get();
    }

    public SimpleStringProperty paymentDateProperty() {
        return paymentDate;
    }
}
