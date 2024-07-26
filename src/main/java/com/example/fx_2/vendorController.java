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
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class vendorController {

    @FXML
    private Label vendorLocation;

    @FXML
    private Label vendor_name;

    @FXML
    private Label vendor_rating;

    @FXML
    private ImageView vendorImage;


//    public void setData(Vendors vendor){
//        Image image = new Image(getClass().getResourceAsStream(vendor.getVendorImage()));
//        vendorImage.setImage(image);
//        vendor_name.setText(vendor.getVendorName());
//        vendor_rating.setText(Double.toString(vendor.getRating()));
//        vendorLocation.setText(vendor.getCity() + ", " + vendor.getState());
//    }

    public void setData(Vendors vendor){
        try {
            String imagePath = vendor.getVendorImage();
            System.out.println("Attempting to load image from path: " + imagePath);
            InputStream is = getClass().getResourceAsStream(imagePath);
            if (is == null) {
                throw new RuntimeException("Cannot load image with path: " + imagePath);
            }
            Image image = new Image(is);
            vendorImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + e.getMessage());
            e.printStackTrace();
        }
        vendor_name.setText(vendor.getVendorName());
        vendor_rating.setText(Double.toString(vendor.getRating()));
        vendorLocation.setText(vendor.getCity() + ", " + vendor.getState());
    }

}
