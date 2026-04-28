package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataBase {
	
	public static void showInvalid() throws IOException {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(SignInController.class.getResource("INVALID.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onDelete(String flightCodeToDelete) throws IOException {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "2912")) {
	        String deletePassengerSql = "DELETE FROM passenger WHERE FlightCode = ?";
	        try (PreparedStatement deletePassengerStatement = connection.prepareStatement(deletePassengerSql)) {
	            deletePassengerStatement.setString(1, flightCodeToDelete);
	            deletePassengerStatement.executeUpdate();
	        }
	        String deletePaymentSql = "DELETE FROM payment WHERE FlightCode = ?";
	        try (PreparedStatement deletePaymentStatement = connection.prepareStatement(deletePaymentSql)) {
	            deletePaymentStatement.setString(1, flightCodeToDelete);
	            deletePaymentStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void cancel(String userToDelete,String flightCodeToDelete) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            deletePassenger(connection, userToDelete, flightCodeToDelete);
            deletePayment(connection, userToDelete, flightCodeToDelete);
            System.out.println("Rows deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void deletePassenger(Connection connection, String username, String flightCode) throws SQLException {
        String deletePassengerQuery = "DELETE FROM passenger WHERE username = ? AND FlightCode = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePassengerQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, flightCode);
            preparedStatement.executeUpdate();
        }
    }
    private static void deletePayment(Connection connection, String username, String flightCode) throws SQLException {
        String deletePaymentQuery = "DELETE FROM payment WHERE username = ? AND FlightCode = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePaymentQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, flightCode);
            preparedStatement.executeUpdate();
        }
	}
	
    public int insert(String user,String pass , String email,String ph , String Location){
            String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
            String username = "root";
            String password = "2912";
            String sql = "INSERT INTO users (username, password, email, phoneNo, location) VALUES (?, ?, ?, ?, ?)";
            try (
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, ph);
                preparedStatement.setString(5, Location);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return -1;
    }
    public String getpass(String specificUsername) throws IOException {
    	String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, specificUsername);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("password");
                    }else {
                    	showInvalid();
                    	return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
    
    public Boolean isflight(String specificUsername) throws IOException {
    	String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT FlightCode FROM Flightdetails WHERE FlightCode = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, specificUsername);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }else {
                    	showInvalid();
                    	return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
    
    public Boolean isAdmin(String specificUsername) {
    	String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT is_admin FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, specificUsername);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBoolean("is_admin");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void deleteFlight(String flightCode) throws IOException {
    	onDelete(flightCode);
        String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        String sql = "DELETE FROM FlightDetails WHERE FlightCode = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, flightCode);
            @SuppressWarnings("unused")
			int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyFlightDate(String flightCode, String newDate) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        String sql = "UPDATE FlightDetails SET DateOfJourney = ? WHERE FlightCode = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newDate);
            preparedStatement.setString(2, flightCode);
            @SuppressWarnings("unused")
			int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    public void putFlight(String flightCode,String flightName,String source, String destination, int capacity ,String dateOfJourney) {
    	String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO FlightDetails (FlightCode, FlightName, Source, Destination, Capacity, DateOfJourney) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, flightCode);
                preparedStatement.setString(2, flightName);
                preparedStatement.setString(3, source);
                preparedStatement.setString(4, destination);
                preparedStatement.setInt(5, capacity);
                preparedStatement.setString(6, dateOfJourney);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Flight details added successfully!");
                } else {
                    System.out.println("Failed to add flight details.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
