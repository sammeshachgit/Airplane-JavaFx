package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class UserController implements Initializable{
	
	@FXML
    private Button BookTicket;
	
	@FXML
	private Button log;
	
	@FXML
	private TableView<Flight> HomeTab2Table;
	
	@FXML
    private TableColumn<Flight, Integer> Capacity;

    @FXML
    private TableColumn<Flight, String> DateOfJourney;

    @FXML
    private TableColumn<Flight, String> Destination;

    @FXML
    private TableColumn<Flight, String> FlightCode;
    
    @FXML
    private TableColumn<Flight, String> FlightName;
    
    @FXML
    private TableColumn<Flight, String> Source;
	
	private String user,fxml;
	public void setUser(String username,String fxml) {
		user=username;
		this.fxml=fxml;
		System.out.println(user+" "+fxml);
	}
	
	@SuppressWarnings("unchecked")
	public void showFlight() {
		HomeTab2Table.getColumns().clear();
	    HomeTab2Table.getItems().clear();
		TableColumn<Flight, String> flightCodeCol = new TableColumn<>("Flight Code");
        flightCodeCol.setCellValueFactory(cellData -> cellData.getValue().flightCodeProperty());
        TableColumn<Flight, String> flightNameCol = new TableColumn<>("Flight Name");
        flightNameCol.setCellValueFactory(cellData -> cellData.getValue().flightNameProperty());
        TableColumn<Flight, String> sourceCol = new TableColumn<>("Source");
        sourceCol.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());
        TableColumn<Flight, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        TableColumn<Flight, Integer> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());
        TableColumn<Flight, String> dateOfJourneyCol = new TableColumn<>("Date of Journey");
        dateOfJourneyCol.setCellValueFactory(cellData -> cellData.getValue().dateOfJourneyProperty());
        //HomeTab2Table.
        HomeTab2Table.getColumns().addAll(flightCodeCol, flightNameCol, sourceCol, destinationCol, capacityCol, dateOfJourneyCol);
        ObservableList<Flight> flightData = getFlightDataFromDatabase();
        HomeTab2Table.setItems(flightData);
	}
	
	public void logout() throws IOException {
		Stage stage = (Stage) log.getScene().getWindow();
		//stage.close();
		String pla = "SignIn.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
	    Parent root = loader.load();
	    //RemoveFlightContriller home = loader.getController();
	    //home.setUser(user,"Admin.fxml");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sign In");
		stage.show();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HomeTab2Table.getColumns().clear();
	    HomeTab2Table.getItems().clear();
		TableColumn<Flight, String> flightCodeCol = new TableColumn<>("Flight Code");
        flightCodeCol.setCellValueFactory(cellData -> cellData.getValue().flightCodeProperty());
        TableColumn<Flight, String> flightNameCol = new TableColumn<>("Flight Name");
        flightNameCol.setCellValueFactory(cellData -> cellData.getValue().flightNameProperty());
        TableColumn<Flight, String> sourceCol = new TableColumn<>("Source");
        sourceCol.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());
        TableColumn<Flight, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        TableColumn<Flight, Integer> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());
        TableColumn<Flight, String> dateOfJourneyCol = new TableColumn<>("Date of Journey");
        dateOfJourneyCol.setCellValueFactory(cellData -> cellData.getValue().dateOfJourneyProperty());
        //HomeTab2Table.
        HomeTab2Table.getColumns().addAll(flightCodeCol, flightNameCol, sourceCol, destinationCol, capacityCol, dateOfJourneyCol);
        ObservableList<Flight> flightData = getFlightDataFromDatabase();
        HomeTab2Table.setItems(flightData);
	}
	
	public void cancelT() throws IOException {
		Stage stage = (Stage) BookTicket.getScene().getWindow();
		//stage.close();
		String pla = "CancelTicket.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
	    Parent root = loader.load();
	    CancelTicketController home = loader.getController();
	    home.setUser(user,"User.fxml");
	    home.call();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Home Up");
		stage.show();
	}
	
	private ObservableList<Flight> getFlightDataFromDatabase() {
        ObservableList<Flight> flightList = FXCollections.observableArrayList();
        String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        String sql = "SELECT * FROM FlightDetails";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String flightCode = resultSet.getString("FlightCode");
                String flightName = resultSet.getString("FlightName");
                String source = resultSet.getString("Source");
                String destination = resultSet.getString("Destination");
                int capacity = resultSet.getInt("Capacity");
                String dateOfJourney = resultSet.getString("DateOfJourney");

                Flight flight = new Flight(flightCode, flightName, source, destination, capacity, dateOfJourney);
                flightList.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightList;
    }
	
	public void BookT() throws IOException {
		Stage stage = (Stage) BookTicket.getScene().getWindow();
		//stage.close();
		String pla = "BookTicket.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
	    Parent root = loader.load();
	    BookTicketController home = loader.getController();
	    home.setUser(user,"User.fxml");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Home Up");
		stage.show();
	}
}