package com.simplilearn.FareCalculator.model;

import java.time.LocalDateTime;

public class Booking {

    private String username;
    private String carLicensePlate;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime pickupTime;
    private LocalDateTime dropoffTime;

    // Default constructor (required for serialization/deserialization)
    public Booking() {}

    // Parameterized constructor
    public Booking(String username, String carLicensePlate, String pickupLocation,
                   String dropoffLocation, LocalDateTime pickupTime, LocalDateTime dropoffTime) {
        this.username = username;
        this.carLicensePlate = carLicensePlate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalDateTime getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(LocalDateTime dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    // ToString method for debugging/logging purposes
    @Override
    public String toString() {
        return "Booking{" +
                "username='" + username + '\'' +
                ", carLicensePlate='" + carLicensePlate + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", pickupTime=" + pickupTime +
                ", dropoffTime=" + dropoffTime +
                '}';
    }
}
