package com.example.fx_2;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnector {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "groupfx";
        String databaseUser = "groupfx";
        String databasePassword = "354411";
        String url = "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/" + databaseName;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
