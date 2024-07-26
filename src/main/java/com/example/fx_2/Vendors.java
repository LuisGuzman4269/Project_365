package com.example.fx_2;

import java.util.List;

public class Vendors {
    private String vendorName;
    private String city;
    private String state;
    private double rating;
    private String vendorImage;
    private List<String> services;


    public Vendors(String vendorName, String city, String state, double rating, String vendorImage, List<String> services) {
        this.vendorName = vendorName;
        this.city = city;
        this.state = state;
        this.rating = rating;
        this.vendorImage = vendorImage;
        this.services = services;
    }


    // Getters and Setters
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVendorImage() {
        return vendorImage;
    }

    public void setVendorImage(String vendorImage) {
        this.vendorImage = vendorImage;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

}
