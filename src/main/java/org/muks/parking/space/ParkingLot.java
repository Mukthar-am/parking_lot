package org.muks.parking.space;

import java.util.HashMap;
import java.util.HashSet;

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
    private HashSet<ParkingSlot> TheParkingLot = new HashSet<>();

    private int LotSize = 0;
    public void setLotSize(int lotSize) { this.LotSize = lotSize; }


    /**
     * Keep adding slots till the LotSize, and return lot-full exception when requested for more slots
     */
}
