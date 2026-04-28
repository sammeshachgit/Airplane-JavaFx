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

public class SignUpController implements Initializable {
	private DataBase obj = new DataBase();
    @FXML
    private Button log;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField location;
    @FXML
    private PasswordField password;
    @FXML
    private Button RegisterButton;
    
    public boolean isValid() {
    	String lo = location.getText().trim();
		String us = username.getText().trim();
	    String ps = password.getText();
	    String ph = phone.getText().trim();
	    String em = email.getText().trim();
		if(us.isEmpty() || ps.isEmpty() || lo.isEmpty() || ph.isEmpty() || em.isEmpty()) {
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
    
    public void Signup() throws IOException {
    	String lo = location.getText().trim();
		String us = username.getText().trim();
	    String ps = password.getText();
	    String ph = phone.getText().trim();
	    String em = email.getText().trim();
	    if(isValid() && obj.isAdmin(us)!=null) {
	    	obj.insert(us, ps, em, ph, lo);
	    	Stage stage = (Stage) log.getScene().getWindow();
			//stage.close();
			String pla = "User.fxml";
			if(obj.isAdmin(us)) {
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
				stage.setTitle("Home Up");
				stage.show();
			}
	    }else {
	    	showInvalid();
	    }
    }
    
    public void login() throws IOException {
		Stage stage = (Stage) log.getScene().getWindow();
		//stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sign In");
		stage.show();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}