package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class passengerController {

    @FXML
    private Label PassengerDetailsLabel;

    @FXML
    private Button close;

    @FXML
    private TableColumn<?, ?> flightCodeColumn;

    @FXML
    private TableColumn<?, ?> nationalityColumn;

    @FXML
    private TableColumn<?, ?> passengerNumberColumn;

    @FXML
    private TableView<?> passengerTable;

    @FXML
    private TableColumn<?, ?> passportNumberColumn;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    void closet(ActionEvent event) throws IOException {
    	Stage stage = (Stage) close.getScene().getWindow();
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
    
    private String user,fxml;
	public void setUser(String username,String fxml) {
		user=username;
		this.fxml=fxml;
		System.out.println(user+" "+fxml);
		//HomeTab1Label.setText("Welcome "+user);
	}

}
