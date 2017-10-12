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


    /** picked for all other than status */
    public void execute(String command, String[] arguments) {

        if (command.equalsIgnoreCase("create_parking_lot")) {
            this.OurParkingLot = new ParkingLot(Integer.parseInt(arguments[1]));
        }

        if (command.equalsIgnoreCase("status")) {
            System.out.println(this.OurParkingLot.toString());
        }

        if (command.equalsIgnoreCase("park")) {
            String vehicleRegNo = arguments[1];
            String vehicleColor = arguments[2];
            System.out.println(this.OurParkingLot.parkVehicle(vehicleRegNo, vehicleColor));
        }

        if (command.equalsIgnoreCase("registration_numbers_for_cars_with_colour")
                || command.equalsIgnoreCase("slot_numbers_for_cars_with_colour")
                || command.equalsIgnoreCase("slot_number_for_registration_number")) {
            String commandArgument = arguments[1];
            System.out.println(this.OurParkingLot.queryLot(command, commandArgument));
        }

        if (command.equalsIgnoreCase("leave")) {
            System.out.println(this.OurParkingLot.releaseSlot(arguments[1]));
        }
    }
}
