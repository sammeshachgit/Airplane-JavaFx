package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController implements Initializable {
	
	private DataBase data = new DataBase();
	
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button loginButton; 
	@FXML
	private Button Register;
	
	public void reg() throws IOException {
		Stage stage = (Stage) loginButton.getScene().getWindow();
		//stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sign Up");
		stage.show();
	}
	
	public boolean isValid() {
		String us = username.getText().trim();
	    String ps = password.getText();
		if(us.isEmpty() || ps.isEmpty()) {
			return false;
		}return true;
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
	
	public void login() throws Exception {
		//System.out.println(isValid());
		if(isValid()) {
			String us = username.getText().trim();
		    String ps = password.getText();
		    String pass = data.getpass(us);
		    //System.out.println(data.isAdmin(us));
		    if(pass==null) {
		    	
		    }
		    else if(pass.equals(ps)) {
		    	Stage stage = (Stage) loginButton.getScene().getWindow();
				//stage.close();
				String pla = "User.fxml";
				if(data.isAdmin(us)) {
					pla="Admin.fxml";
					FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
			        Parent root = loader.load();
			        AdminController home = loader.getController();
			        home.setUser(us,pla);
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Home Up");
					stage.show();
				}
				else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(pla));
			        Parent root = loader.load();
			        UserController home = loader.getController();
			        home.setUser(us,pla);
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Home Page");
					stage.show();
				}
		    }else {
		    	//System.out.println(pass + " "+ ps);
		    	showInvalid();
		    }
		}else {
			showInvalid();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}