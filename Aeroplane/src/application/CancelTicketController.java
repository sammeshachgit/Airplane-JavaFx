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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CancelTicketController implements Initializable {

	DataBase obj = new DataBase();
	
	 @FXML
	 private TextField FlightCode;
	 
    @FXML
    private Button close;
    
    @FXML
    private TableColumn<Payment, String> USERNAME;

    @FXML
    private TableColumn<Payment, String> FLIGHTCODE;

    @FXML
    private TableColumn<Payment, String> CARDNO;

    @FXML
    private TableColumn<Payment, Double> AMOUNT;

    @FXML
    private TableColumn<Payment, String> PAYDATE;

    @FXML
    private TableView<Payment> PaymentDetailsTable;
    
    @FXML
    private Button CancelBK;
    
    private String user,fxml;
	public void setUser(String username,String fxml) {
		user=username;
		this.fxml=fxml;
		System.out.println(user+" "+fxml);
		//HomeTab1Label.setText("Welcome "+user);
	}
    
    public void closet() throws IOException {
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//System.out.println("0");
        call();
    }
    
    public void call() {
    	initializeTableColumns();
        try {
			loadPaymentData();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void initializeTableColumns() {
        USERNAME.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        FLIGHTCODE.setCellValueFactory(cellData -> cellData.getValue().flightCodeProperty());
        CARDNO.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty());
        AMOUNT.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        PAYDATE.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty());
    }

    private void loadPaymentData() throws IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/javafx";
        String username = "root";
        String password = "2912";
        String sql = "SELECT * FROM payment WHERE username = ?";
        //System.out.println("HELLO");
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setString(1, user);
            //preparedStatement.setString(1, user);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ObservableList<Payment> paymentData = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    String us = resultSet.getString("username");
                    String flightCode = resultSet.getString("FlightCode");
                    String cardNumber = resultSet.getString("cardNumber");
                    double amount = resultSet.getDouble("amount");
                    String paymentDate = resultSet.getString("paymentDate");

                    Payment payment = new Payment(us, flightCode, cardNumber, amount, paymentDate);
                    paymentData.add(payment);
                }

                PaymentDetailsTable.setItems(paymentData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    
    public Boolean valid() throws IOException {
    	String s= FlightCode.getText();
    	if(s.isEmpty() || obj.isflight(s))return false;
    	return true;
    }
    
    public void cancelTicket() throws IOException {
    	String s= FlightCode.getText();
    	if(!valid()) {
    		obj.cancel(user,s);
    		closet();
    	}else {
    		showInvalid();
    	}
    }

}
