package org.muks.parking.space;

import org.muks.parking.entities.Vehicle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 300000511 on 10/10/17
 * Parking Lot object holding most of the query'ble information
 */


public class ParkingLot {
    /** Store map based on color as the key for faster search */
    private HashMap<String, ParkingSlot> VehiclesByColor = new HashMap<>();

    /** Store map based on RegNo as the key for faster search */
    private HashMap<String, ParkingSlot> VehiclesByRegNo = new HashMap<>();

    /** Core parking-lot object having all the slots */
    private TreeMap<Integer, ParkingSlot> TheParkingLot = new TreeMap<>();


    private int LotSize = 0;    /** init to some slot sizes */

    private int TotalVehicles = 0;    /** init to some slot sizes */

    public ParkingLot(int lotSize) {
        this.LotSize = lotSize;
        for (int slotId = 1; slotId <= lotSize; slotId++) {
            this.TheParkingLot.put(slotId, new ParkingSlot(slotId));
        }

        System.out.println("Slots: "+ this.TheParkingLot.size());

    }

    public int getLotSize() {
        return this.LotSize;
    }

    /**
     * Keep adding slots till the LotSize, and return lot-full exception when requested for more slots
     */
    public void parkVehicle(String vehicleRegNo, String vehicleColor) {
        ParkingSlot parkingSlot = getSlotCloseby();     /** Get empty slot, close by */
        if (parkingSlot != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNumber(vehicleRegNo);
            vehicle.setColor(vehicleColor);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String inDateTime = dateFormat.format(date);

            parkingSlot.setInDateTime(inDateTime);
            parkingSlot.setVehicleIn(vehicle);

            /** Issue parking ticket */
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicket.setSlot(parkingSlot);
            parkingTicket.setInDateTime(inDateTime);

            parkingSlot.issueParkingTicket(parkingTicket);

        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }


    private ParkingSlot getSlotCloseby() {
        ParkingSlot emptySlot = null;
        for (int slotId = 1; slotId <= this.TheParkingLot.size(); slotId++) {
            ParkingSlot parkingSlot = this.TheParkingLot.get(slotId);
            if (!parkingSlot.isOccupied()) {
                emptySlot = parkingSlot;
                break;
            }
        }

        return emptySlot;
    }


    public String toString() {
        StringBuilder parkingLotDetails = new StringBuilder("\n");
        if (this.TheParkingLot.size() == 0) {
            parkingLotDetails.append("Empty parking lot.");
        }
        else {
            for (int slotId = 1; slotId <= this.TheParkingLot.size(); slotId++) {
                ParkingSlot parkingSlot = this.TheParkingLot.get(slotId);
                System.out.println(parkingSlot.getSlotId() + "\t" +
                        parkingSlot.getVehicleIn().getRegistrationNumber() + "\t" +
                        parkingSlot.getVehicleIn().getColor()
                );
            }
        }
        return parkingLotDetails.toString();
    }
}
