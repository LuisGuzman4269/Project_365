package com.example.fx_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView checkImageView; //so image can be seen

    @FXML
    private Button closeButton;

    @FXML
    private Label userRegisteredLabel;

    @FXML
    private Label noMatchLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField passwordTextField2;

    @FXML
    private TextField fnTextField;

    @FXML
    private TextField lnTextField;

    @FXML
    private TextField usernameTextField;



    public void initialize(URL url, ResourceBundle rb) {
        File checkFile = new File("Images/check.png");
        Image checkImage = new Image(checkFile.toURI().toString());
        checkImageView.setImage(checkImage);
    }

    public void registerButtonAction(ActionEvent event) { //register button is pressed
        if (passwordTextField.getText().equals(passwordTextField2.getText())) {
            registerUser();
            noMatchLabel.setText("you are set");
            lookAtBusinesses();
        }
        else{
            noMatchLabel.setText("password does not match!");
        }
    }

    public void registerUser(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = connector.getConnection();

        String firstname = fnTextField.getText();
        String lastname = lnTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        String insertFields = "INSERT INTO Users (username, fname, lname, password) VALUES ('";
        String insertValues = username + "','" + firstname + "','" + lastname + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;
        System.out.println(insertToRegister);

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);
            userRegisteredLabel.setText("User has been registered successfully");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }



    @FXML
    public void cancelLoginOnActionR(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void lookAtBusinesses() {
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

}
