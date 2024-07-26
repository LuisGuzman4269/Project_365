package com.example.fx_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Button cancelLoginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brand; //so image can be seen

    @FXML
    private ImageView lock; // so image can be seen

    @FXML
    private PasswordField passwordBox;

    @FXML
    private TextField usernameTextField;

    private Button loginButton;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File brandingFile = new File("Images/mitiara.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brand.setImage(brandingImage);

        File lockFile = new File("Images/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lock.setImage(lockImage);
    }

    public void registerButtonAction(ActionEvent actionEvent) { //register button pressed
        createAccountForm();
    }


    public void loginButtonOnAction(ActionEvent event) { //login button pressed
         if (usernameTextField.getText().isBlank() == false && passwordBox.getText().isBlank() == false) {
            validate_login();

         } else {
             loginMessageLabel.setText("Please enter your username and password");
         }

    }

    public void main_menu(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(mitiarra.class.getResource("businessListView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    private void validate_login() {
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connectDB = dbConnector.getConnection();

        String verifyLogin = "SELECT count(1) FROM Users WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordBox.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Login Successful");
                    main_menu();
                }

                else {
                    loginMessageLabel.setText("Login Failed. Try again");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void cancelLoginOnAction(ActionEvent event) { //cancel button is pressed application terminates
        Stage stage = (Stage) cancelLoginButton.getScene().getWindow();
        stage.close();
    }

    public void createAccountForm(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(mitiarra.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 567);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }


}