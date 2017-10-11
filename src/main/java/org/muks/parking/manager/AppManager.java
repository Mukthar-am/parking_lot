package org.muks.parking.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by 300000511 on 09/10/17
 * Parking_Lot application manager - main
 */


public class AppManager {
    private static Logger LOG = LoggerFactory.getLogger(AppManager.class);
    private static boolean keepOperational = true;

    private static String CommandUsage = "\n" +
            "Valid commands are: park | " +
            "create_parking_lot | " +
            "status | " +
            "leave | " +
            "registration_numbers_for_cars_with_colour | " +
            "slot_number_for_registration_number | " +
            "slot_numbers_for_cars_with_colour";

    private static String ArgumentUsage = "\n" +
            "Other than command = status, all other commands do have arguments as below\n" +
            "create_parking_lot <total slots>\n" +
            "status <no-arg>\n" +
            "leave <slot-id>\n" +
            "registration_numbers_for_cars_with_colour <color-of-the-vehicle\n" +
            "slot_number_for_registration_number <reg.no> " +
            "slot_numbers_for_cars_with_colour <color>";

    ParkingLotOperator ParkingLotOperatorInstance = null;
    Thread OperatorThread = null;


    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        appManager.operator();
    }


    public AppManager() {
        /** have a parking lot operator */
        ParkingLotOperatorInstance = new ParkingLotOperator();
        OperatorThread = new Thread(ParkingLotOperatorInstance);
        OperatorThread.start();
        ParkingLotOperatorInstance.startParkingLot();

    }


    public void operator() {
        LOG.debug("# Opening up parking lot for occupancy.... ");

        if (OperatorThread.isAlive())
            LOG.info("OperatorThread is alive");
        else
            LOG.info("Not alive...");

        while (keepOperational) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Input command...?\n");
            String input = scanner.nextLine();

            /** gracefull kill */
            if (input.equalsIgnoreCase("end")) {
                LOG.debug("# Shutting down parking facility now... ");
                ParkingLotOperatorInstance.shutdownParkingLot();
                System.exit(0);
            }

            /** deligate the input - all for enabling unit testing */
            LOG.info( operate(input) );
        }
    }


    public String operate(String input) {
        String returnMsg = null;

        String[] values = input.split(" ");
        String command = values[0];

        if (!isValidInput(command)) {
            returnMsg = "\nWarning: Invalid command usage: " + CommandUsage + "\n";
        } else {
            if (!command.equalsIgnoreCase("status") && values.length == 1) {
                returnMsg = "\nWarning: Invalid arguments: " + ArgumentUsage + "\n";
            } else {
                if (command.equalsIgnoreCase("status")) {
                    ParkingLotOperatorInstance.setCommandAndArgument(command);
                } else {
                    String argument = values[1];
                    ParkingLotOperatorInstance.setCommandAndArgument(command, values);
                }

                /** Wait for operation to complete before prompting for another user input */
                boolean didOperationComplete = false;
                while (!didOperationComplete) {
                    didOperationComplete = ParkingLotOperatorInstance.isOperationCompleted();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                returnMsg = "| Successfully complete the operation: \"" + command + "\" |";
            }
        }

        return returnMsg;
    }

    private static boolean isValidInput(String command) {
        if (command.equalsIgnoreCase("create_parking_lot") ||
                command.equalsIgnoreCase("park") ||
                command.equalsIgnoreCase("leave") ||
                command.equalsIgnoreCase("status") ||
                command.equalsIgnoreCase("registration_numbers_for_cars_with_colour") ||
                command.equalsIgnoreCase("slot_numbers_for_cars_with_colour") ||
                command.equalsIgnoreCase("slot_number_for_registration_number")
                ) {
            return true;
        }

        return false;
    }
}
