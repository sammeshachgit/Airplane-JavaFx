package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFlightController implements Initializable{

	DataBase obj = new DataBase();
	//AddFlightController a = new AddFlightController();
	
    @FXML
    private Button addflight;

    @FXML
    private Button clear;

    @FXML
    private Button close;

    @FXML
    private DatePicker data;

    @FXML
    private TextField flightcapacity;

    @FXML
    private TextField flightcode;

    @FXML
    private TextField flightdestination;

    @FXML
    private TextField flightname;

    @FXML
    private TextField flightsourse;
    
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
		Stage stage = (Stage) close.getScene().getWindow();
		//stage.close();
		String pla = "Admin.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
        Parent root = loader.load();
        AdminController home = loader.getController();
        home.setUser(user,pla);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Home Up");
		stage.show();
	}
	
	public void clear() {
		String empty = new String();
		flightcode.setText(empty);
		flightdestination.setText(empty);
		flightname.setText(empty);
		flightsourse.setText(empty);
		flightcapacity.setText(empty);
		data.setValue(null);
	}
	
	public void add() throws IOException {
		try {
	        int capacity = Integer.parseInt(flightcapacity.getText().trim());
	        String code = flightcode.getText();
	        String destiny = flightdestination.getText();
	        String name = flightname.getText();
	        String source = flightsourse.getText();
	        LocalDate day = data.getValue();
	        Date sqlDate = Date.valueOf(day);
	        System.out.println(sqlDate);
	        //String dateString = day.formatted(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        if (code.isEmpty() || destiny.isEmpty() || name.isEmpty() || source.isEmpty() || sqlDate == null ) {
	            showInvalid();
	        }
	        else
	        obj.putFlight(code, name, source, destiny, capacity, sqlDate.toString());
	        closet();
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid capacity format. Please enter a valid number.");
	    }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}