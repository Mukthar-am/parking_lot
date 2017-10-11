package org.muks.parking.entities;

/**
 * Created by 300000511 on 11/10/17
 * Vehicle information
 */


public class Vehicle {
    private String Color = null;
    private String RegistrationNumber = null;

    public Vehicle() {}

    public void setColor(String color) { this.Color = color; }
    public void setRegistrationNumber(String regNo) { this.RegistrationNumber = regNo; }

    // getters
    public String getColor() { return this.Color; }
    public String getRegistrationNumber() { return this.RegistrationNumber; }

    public String toString() {
        StringBuilder vehicleDetails = new StringBuilder("[");
        vehicleDetails.append("Registration Number: " + this.RegistrationNumber);
        vehicleDetails.append("Color: " + this.Color);
        vehicleDetails.append("]");

        return vehicleDetails.toString();
    }
}
