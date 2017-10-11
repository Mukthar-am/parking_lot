package org.muks.parking.space;

import org.muks.parking.entities.Vehicle;

import java.util.Date;

/**
 * Created by 300000511 on 10/10/17
 * Parking Ticket - having slot and vehicle details.
 */

public class ParkingTicket {
    private Date InDateTime = new Date();
    private Date OutDateTime = new Date();
    private ParkingSlot ParkedSlot = new ParkingSlot();
    //private Vehicle ParkedVehicle = new Vehicle();

    public void setSlot(ParkingSlot slot) { this.ParkedSlot = slot; }
    //public void setVehicle(Vehicle vehicle) { this.ParkedVehicle = vehicle; }

    public ParkingSlot getSlot() { return this.ParkedSlot; }
    //public Vehicle getVehicle() { return this.ParkedVehicle; }

    public String toString() {
        StringBuilder ticketDetails = new StringBuilder("{");

        ticketDetails.append("In Time: " + this.InDateTime);
        ticketDetails.append("Out Time: " + this.OutDateTime);
        ticketDetails.append("ParkingSlot: " + this.ParkedSlot.toString());
        //ticketDetails.append("Vehicle: " + this.ParkedVehicle.toString());
        ticketDetails.append("}");

        return ticketDetails.toString();
    }
}
