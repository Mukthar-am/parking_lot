package org.muks.parking.space;

import org.muks.parking.entities.Vehicle;


/**
 * Created by 300000511 on 10/10/17.
 */

public class ParkingSlot {
    private int SlotId = -1;                    /** Slot ID */
    private Vehicle VehicleIn = new Vehicle();  /** Vehicle occupying slot */
    private boolean IsOccupied = false;         /** Is occupied or not, false-NOT to start with */

    /** constructor with ID initiater */
    public ParkingSlot() {}
    public ParkingSlot(int id) { this.SlotId = id; }

    /** getters */
    public boolean isOccupied() { return this.IsOccupied; }
    public Vehicle getVehicleIn() { return this.VehicleIn; }
    public int getSlotId() { return this.SlotId; }


    public void setVehicleIn(Vehicle vehicleIn) { this.VehicleIn = vehicleIn; }
    public void setOccupied() { this.IsOccupied = true; }
}
