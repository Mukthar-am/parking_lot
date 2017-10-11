package org.muks.parking.utils;

import org.muks.parking.space.ParkingLot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 300000511 on 11/10/17.
 */
public class CommandExecutor {
    private static Logger LOG = LoggerFactory.getLogger(CommandExecutor.class);
    ParkingLot OurParkingLot = null;

    /** Will be picked especially for status */
    public void execute(String command) {
    }


    /** picked for all other than status */
    public void execute(String command, String[] arguments) {

        if (command.equalsIgnoreCase("create_parking_lot")) {
            this.OurParkingLot = new ParkingLot(Integer.parseInt(arguments[1]));
            LOG.info("Parking lot initialised with \"" + arguments[1] + "\" slots.");
        }

        if (command.equalsIgnoreCase("status")) {
            LOG.info(this.OurParkingLot.toString());
        }

        if (command.equalsIgnoreCase("park")) {
            String vehicleRegNo = arguments[1];
            String vehicleColor = arguments[2];
            this.OurParkingLot.parkVehicle(vehicleRegNo, vehicleColor);
        }
    }
}
