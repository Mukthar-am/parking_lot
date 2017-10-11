package org.muks.parking.space;

import org.muks.parking.entities.Vehicle;

import java.util.Date;


/**
 * Created by 300000511 on 10/10/17.
 */

public class ParkingSlot {
    private int SlotId = -1;                    /** Slot ID */
    private Vehicle VehicleIn = new Vehicle();  /** Vehicle occupying slot */
    private boolean IsOccupied = false;         /** Is occupied or not, false-NOT to start with */

    private String InDateTime = null;
    private String OutDateTime = null;

    private ParkingTicket ParkedTicket = new ParkingTicket();

    /** constructor with ID initiater */
    public ParkingSlot() {}
    public ParkingSlot(int id) { this.SlotId = id; }

    /** getters */
    public boolean isOccupied() { return this.IsOccupied; }
    public Vehicle getVehicleIn() { return this.VehicleIn; }
    public int getSlotId() { return this.SlotId; }

    public void setVehicleIn(Vehicle vehicleIn) {
        this.VehicleIn = vehicleIn;
        this.IsOccupied = true;
    }

    public void releaseSlot() {
        this.VehicleIn = new Vehicle();
        this.IsOccupied = false;
    }

    public void setInDateTime(String inDateTime) {
        this.InDateTime = inDateTime;
    }

    public void setOutDateTime(String outDateTime) {
        this.OutDateTime = outDateTime;
    }

    public void issueParkingTicket(ParkingTicket parkingTicket) {
        this.ParkedTicket = parkingTicket;
    }


    public String toString() {
        StringBuilder slotDetails = new StringBuilder("[");
        slotDetails.append("Slot ID: " + this.SlotId);
        slotDetails.append("Is Occupied: " + this.IsOccupied);
        slotDetails.append("In Time: " + this.InDateTime);
        slotDetails.append("Out Time: " + this.OutDateTime);
        slotDetails.append("Vehicle: " + this.VehicleIn.toString());
        slotDetails.append("Parking Ticket: " + this.ParkedTicket.toString());
        slotDetails.append("]");

        return slotDetails.toString();
    }
}
