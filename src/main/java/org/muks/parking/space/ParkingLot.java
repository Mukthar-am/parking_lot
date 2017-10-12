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
    //private HashMap<String, HashSet<ParkingSlot>> VehiclesByColor = new HashMap<>();
    private HashMap<String, TreeMap<Integer, ParkingSlot>> VehiclesByColor = new HashMap<>();

    /** Store map based on RegNo as the key for faster search */
    private HashMap<String, ParkingSlot> VehiclesByRegNo = new HashMap<>();
    //private HashMap<String, TreeMap<Integer, ParkingSlot>> VehiclesByRegNo = new HashMap<>();

    /** Core parking-lot object having all the slots */
    private TreeMap<Integer, ParkingSlot> TheParkingLot = new TreeMap<>();


    private int LotSize = 0;    /** init to some slot sizes */

    private int TotalVehicles = 0;    /** init to some slot sizes */

    public ParkingLot(int lotSize) {
        this.LotSize = lotSize;
        for (int slotId = 1; slotId <= lotSize; slotId++) {
            this.TheParkingLot.put(slotId, new ParkingSlot(slotId));
        }

        System.out.println("Created a parking lot with " + this.TheParkingLot.size() + " slots");

    }

    public int getLotSize() {
        return this.LotSize;
    }

    /**
     * Keep adding slots till the LotSize, and return lot-full exception when requested for more slots
     */
    public String parkVehicle(String vehicleRegNo, String vehicleColor) {
        StringBuilder parkedOutput = new StringBuilder();

        /** flattening strings to lower case to avoid case conflicts. */
        vehicleColor = vehicleColor.toLowerCase();
        vehicleRegNo = vehicleRegNo.toLowerCase();

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


            /** For all of our analytics and query purpose */
            /** collection by color */
            if (this.VehiclesByColor.containsKey(vehicleColor)) {
                this.VehiclesByColor.get(vehicleColor).put(parkingSlot.getSlotId(), parkingSlot);
            }
            else {
                this.VehiclesByColor.put(vehicleColor, new TreeMap<>());
                this.VehiclesByColor.get(vehicleColor).put(parkingSlot.getSlotId(), parkingSlot);
            }


            /** collection by reg-no.
             * By the implementation of HashMap, It overrides the value if the key is already found, taking an
             * advantage of overriding parking ticket, in and out timings if the same vehicle appears for the next time */
            this.VehiclesByRegNo.put(vehicleRegNo, parkingSlot);

            parkedOutput.append("Allocated slot number: " + parkingSlot.getSlotId());

        } else {
            parkedOutput.append("Sorry, parking lot is full");
        }

        return parkedOutput.toString();
    }



    public String queryLot(String query, String queryBy) {
        StringBuilder queryOutput = new StringBuilder();

        if (query.equalsIgnoreCase("registration_numbers_for_cars_with_colour")
                || query.equalsIgnoreCase("slot_numbers_for_cars_with_colour") ) {

            TreeMap<Integer, ParkingSlot> slotsByColor = this.VehiclesByColor.get(queryBy.toLowerCase());
            Set<Integer> slotIds = slotsByColor.keySet();
            Iterator<Integer> slotIdIterator = slotIds.iterator();
            while (slotIdIterator.hasNext()) {
                Integer slotId = slotIdIterator.next();
                ParkingSlot parkignSlot = slotsByColor.get(slotId);

                if (query.equalsIgnoreCase("registration_numbers_for_cars_with_colour"))
                    queryOutput.append(parkignSlot.getVehicleIn().getRegistrationNumber());

                if (query.equalsIgnoreCase("slot_numbers_for_cars_with_colour"))
                    queryOutput.append(parkignSlot.getSlotId());


                if (slotIdIterator.hasNext())
                    queryOutput.append(", ");
            }
        }


        if (query.equalsIgnoreCase("slot_number_for_registration_number")) {
            if (this.VehiclesByRegNo.containsKey(queryBy.toLowerCase()))
                queryOutput.append(this.VehiclesByRegNo.get(queryBy.toLowerCase()).getSlotId());
            else
                queryOutput.append("Not found");
        }

        return queryOutput.toString();
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


    public String releaseSlot(String commandArgument) {
        int slotId = Integer.parseInt(commandArgument);
        ParkingSlot parkingSlot = this.TheParkingLot.get(slotId);

        StringBuilder releaseStatus = new StringBuilder();

        /** drop the slot from the parking lot */
        this.TheParkingLot.put(slotId, new ParkingSlot(slotId));
        releaseStatus.append("Slot number " + commandArgument + " is free");

        this.VehiclesByColor
                .get(parkingSlot.getVehicleIn().getColor())
                .remove(parkingSlot.getSlotId());

        this.VehiclesByRegNo
                .remove(parkingSlot.getVehicleIn().getRegistrationNumber());

        return releaseStatus.toString();
    }

    public String toString() {
        StringBuilder parkingLotDetails = new StringBuilder();
        if (this.TheParkingLot.size() == 0) {
            parkingLotDetails.append("Empty parking lot.");
        }
        else {
            parkingLotDetails.append("Slot No" + "\t" + "Registration No." + "\t" + "Color");

            for (int slotId = 1; slotId <= this.TheParkingLot.size(); slotId++) {
                ParkingSlot parkingSlot = this.TheParkingLot.get(slotId);

                /** Skip printing empty parking slot */
                if (parkingSlot.getVehicleIn().getRegistrationNumber() == null)
                    continue;

                parkingLotDetails.append("\n" +
                        parkingSlot.getSlotId() + "\t" +
                        parkingSlot.getVehicleIn().getRegistrationNumber() + "\t" +
                        parkingSlot.getVehicleIn().getColor()
                );
            }
        }
        return parkingLotDetails.toString();
    }
}
