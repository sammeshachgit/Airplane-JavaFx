package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dummyDB{
	public static void main(String[] args) {
			String flightCode = "FFFF98";
			String newDate = "2000-01-01";
		    String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
		    String username = "root";
		    String password = "2912";
		    String sql = "UPDATE FlightDetails SET DateOfJourney = ? WHERE FlightCode = ?";

		    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
		         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

		        preparedStatement.setString(1, newDate);
		        preparedStatement.setString(2, flightCode);

		        int rowsAffected = preparedStatement.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("Flight with code " + flightCode + " date modified successfully.");
		            // Optionally, you can refresh the table data after modification
		            //showFlight();
		        } else {
		            System.out.println("Flight with code " + flightCode + " not found.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

    }
}