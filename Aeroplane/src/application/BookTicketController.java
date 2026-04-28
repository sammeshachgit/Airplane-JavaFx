package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookTicketController implements Initializable{

	DataBase obj = new DataBase();
	
    @FXML
    private Button Book;

    @FXML
    private Label BookTicketAmountLabel;

    @FXML
    private Label BookTicketLabel;

    @FXML
    private Label BookTicketPassengerDetailsLabel;

    @FXML
    private Label BookTicketPaymentDateLabel;

    @FXML
    private Label BookTicketPaymentDetailsLabel;

    @FXML
    private TableView<Flight> HomeTab2Table;

    @FXML
    private TableColumn<Flight, Integer> Capacity;

    @FXML
    private TableColumn<Flight, String> FlightCode;

    @FXML
    private TableColumn<Flight, String> DateOfJourney;

    @FXML
    private TableColumn<Flight, String> Destination;

    @FXML
    private TableColumn<Flight, String> FlightName;

    @FXML
    private TextField Nationality;

    @FXML
    private TextField PassName;

    @FXML
    private TextField Passport;

    @FXML
    private TextField Phone;

    @FXML
    private TableColumn<Flight, String> Source;

    @FXML
    private TextField flightcode;
    
    @FXML
    private TextField amount;

    @FXML
    private TextField card;

    @FXML
    private Button closeB;

    @FXML
    private DatePicker paydate;

    private String user,fxml;
	public void setUser(String username,String fxml) {
		user=username;
		this.fxml=fxml;
		System.out.println(user+" "+fxml);
		//HomeTab1Label.setText("Welcome "+user);
	}
	
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
	
	public void closet() throws IOException {
		Stage stage = (Stage) closeB.getScene().getWindow();
		//stage.close();
		String pla = fxml;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
        Parent root = loader.load();
        if(pla=="Admin.fxml") {
        	AdminController home = loader.getController();
            home.setUser(user,pla);
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("Home Up");
    		stage.show();
        }else {
        	UserController home = loader.getController();
            home.setUser(user,pla);
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("Home Up");
    		stage.show();
        }
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
	
	public Boolean validate() throws IOException {
	    if (Nationality.getText().isEmpty() || PassName.getText().isEmpty() || Passport.getText().isEmpty() || 
	        Phone.getText().isEmpty() || amount.getText().isEmpty() || card.getText().isEmpty() || 
	        paydate.getValue() == null || flightcode.getText().isEmpty() || obj.isflight(flightcode.getText())) {
	        return false;
	    }
	    return true;
	}

	
	public void onsubmit() throws IOException {
	    if (!validate()) {
	        String Nation = Nationality.getText();
	        String pn = PassName.getText();
	        String passport = Passport.getText();
	        String PhNo = Phone.getText();
	        String amt = amount.getText();
	        String Dcard = card.getText();
	        Date day = Date.valueOf(paydate.getValue());
	        String fcd = flightcode.getText();

	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "2912")) {
	            String passengerSql = "INSERT INTO passenger (username, FlightCode, passenger_number, passport_number, Nationality) VALUES (?, ?, ?, ?, ?)";
	            try (PreparedStatement passengerStatement = connection.prepareStatement(passengerSql)) {
	                passengerStatement.setString(1, pn);
	                passengerStatement.setString(2, fcd);
	                passengerStatement.setString(3, PhNo);
	                passengerStatement.setString(4, passport);
	                passengerStatement.setString(5, Nation);
	                passengerStatement.executeUpdate();
	            }
	            String paymentSql = "INSERT INTO payment (username, FlightCode, cardNumber, amount, paymentDate) VALUES (?, ?, ?, ?, ?)";
	            try (PreparedStatement paymentStatement = connection.prepareStatement(paymentSql)) {
	                paymentStatement.setString(1, pn); 
	                paymentStatement.setString(2, fcd);
	                paymentStatement.setString(3, Dcard);
	                paymentStatement.setBigDecimal(4, new BigDecimal(amt));
	                paymentStatement.setDate(5, day);
	                paymentStatement.executeUpdate();
	            }
	            closet();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        showInvalid();
	    }
	}

}
