package com.example.fx_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class BusinessController implements Initializable {



    @FXML
    private ImageView cateringImageView;

    @FXML
    private ImageView cateringImageView2;

    @FXML
    private ImageView cateringImageView3;

    @FXML
    private ImageView cateringImageView4;

    @FXML
    private ImageView cateringImageView5;

    @FXML
    private ImageView cateringImageView6;

    @FXML
    private ImageView cateringImageView7;

    @FXML
    private ImageView menu;

    @FXML
    private Button filter;

    @FXML
    private ImageView searchImageView;

    @FXML
    private GridPane servicesGrid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File cateringFile = new File("Images/catering.png");
        Image cateringImage = new Image(cateringFile.toURI().toString());
        cateringImageView.setImage(cateringImage);
        cateringImageView2.setImage(cateringImage);
        cateringImageView3.setImage(cateringImage);
        cateringImageView4.setImage(cateringImage);
        cateringImageView5.setImage(cateringImage);
        cateringImageView6.setImage(cateringImage);
        cateringImageView7.setImage(cateringImage);



        File search_icon_File = new File("Images/search_icon.png");
        Image searchImage = new Image(search_icon_File.toURI().toString());
        searchImageView.setImage(searchImage);

        File menu_File = new File("Images/menu_pic.jpg");
        Image menuImage = new Image(menu_File.toURI().toString());
        menu.setImage(menuImage);

        // Create an instance of DatabaseConnector
        DatabaseConnector databaseConnector = new DatabaseConnector();
        // Create an instance of VendorService
        VendorService vendorService = new VendorService(databaseConnector);

        int column = 0;
        int row = 1;
        // Call the getVendors method to retrieve the list of vendors
        List<Vendors> vendors = vendorService.getVendors();

        // Display vendor information in the UI
//        printVendorInfo(vendorService);

        try {
            for (Vendors vendor : vendors) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("vendor.fxml"));
                Pane pane = fxmlLoader.load();
                vendorController vendorController = fxmlLoader.getController();
                vendorController.setData(vendor);

                if(column == 3){
                    column = 0;
                    ++row;
                }
                servicesGrid.add(pane, column++, row);
                servicesGrid.setMargin(pane, new Insets(30, 30, 30, 30));//originally just one v
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void printVendorInfo(VendorService vendorService) {
        List<Vendors> vendors = vendorService.getVendors();

        for (Vendors vendor : vendors) {
            System.out.println("Vendor Name: " + vendor.getVendorName());
            System.out.println("City: " + vendor.getCity());
            System.out.println("State: " + vendor.getState());
            System.out.println("Rating: " + vendor.getRating());
            System.out.println("Image: " + vendor.getVendorImage());
            System.out.println("Services: " + vendor.getServices());
        }
    }
}
