package com.example.fx_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class mitiarra extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mitiarra.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();


//        DatabaseConnector databaseConnector = new DatabaseConnector();
//        VendorService vendorService = new VendorService(databaseConnector);
//
//        // Retrieve and print vendor information
//        printVendorInfo(vendorService);

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

    public static void main(String[] args) {
        launch();

    }
}