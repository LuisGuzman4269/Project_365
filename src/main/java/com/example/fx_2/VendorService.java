package com.example.fx_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorService {

    private DatabaseConnector databaseConnector;

    public VendorService(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public List<Vendors> getVendors() {
        List<Vendors> vendorList = new ArrayList<>();
        String query = "SELECT v.vendor_name, v.city, v.state, v.rating, v.vendor_image, s.ser_name " +
                "FROM Vendors v " +
                "JOIN VendorServices vs ON v.vendor_id = vs.vendor_id " +
                "JOIN Services s ON vs.service_id = s.service_id " +
                "ORDER BY v.vendor_id;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            Map<String, Vendors> vendorMap = new HashMap<>();

            while (rs.next()) {
                String vendorName = rs.getString("vendor_name");
                String city = rs.getString("city");
                String state = rs.getString("state");
                double rating = rs.getDouble("rating");
                String vendorImage = rs.getString("vendor_image");
                String serviceName = rs.getString("ser_name");

                Vendors vendor = vendorMap.get(vendorName);
                if (vendor == null) {
                    List<String> services = new ArrayList<>();
                    services.add(serviceName);
                    vendor = new Vendors(vendorName, city, state, rating, vendorImage, services);
                    vendorMap.put(vendorName, vendor);
                } else {
                    vendor.getServices().add(serviceName);
                }
            }

            vendorList.addAll(vendorMap.values());
            System.out.println("Retrieved vendors: " + vendorList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendorList;
    }
}
