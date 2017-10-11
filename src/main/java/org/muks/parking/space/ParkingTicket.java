package org.muks.parking.space;

import org.muks.parking.entities.Vehicle;
import org.muks.parking.utils.GeneralUtils;

import java.util.Date;

/**
 * Created by 300000511 on 10/10/17
 * Parking Ticket - having slot and vehicle details.
 */

public class ParkingTicket {
    private String InDateTime = null;
    private String OutDateTime = null;
    private ParkingSlot ParkedSlot = new ParkingSlot();


    public void setSlot(ParkingSlot slot) { this.ParkedSlot = slot; }
    public void setInDateTime(String inDateTime) { this.InDateTime = inDateTime; }
    public void setOutDateTime(String outDateTime) { this.OutDateTime = outDateTime; }

    public ParkingSlot getSlot() { return this.ParkedSlot; }

    public long getDuration() {
        return GeneralUtils.getDuration(this.InDateTime, this.OutDateTime);
    }


    public String toString() {
        StringBuilder ticketDetails = new StringBuilder("(");
        ticketDetails.append("In Time: " + this.InDateTime);
        ticketDetails.append("Out Time: " + this.OutDateTime);
        ticketDetails.append("ParkingSlot: " + this.ParkedSlot.toString());
        ticketDetails.append("Duration: " + getDuration());
        ticketDetails.append(")");

        return ticketDetails.toString();
    }
}
