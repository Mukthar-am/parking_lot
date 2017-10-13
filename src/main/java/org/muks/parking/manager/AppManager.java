package org.muks.parking.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by 300000511 on 09/10/17
 * Parking_Lot application manager - main
 */


public class AppManager {
    //private static Logger LOG = LoggerFactory.getLogger(AppManager.class);
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

    private ParkingLotOperator ParkingLotOperatorInstance = null;
    private Thread OperatorThread = null;

    /** Constructor overloading for init of the Thread class and thread object */
    public AppManager() {
        /** have a parking lot operator */
        ParkingLotOperatorInstance = new ParkingLotOperator();
        OperatorThread = new Thread(ParkingLotOperatorInstance);
        OperatorThread.start();
        ParkingLotOperatorInstance.startParkingLot();

        /** graceful shutdown */
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());
    }


    public static void main(String[] args) {
        AppManager appManager = new AppManager();

        if (args.length == 0) {     /** If no args, then interact with user from CLI/Java-Scanner */
            appManager.operator();
        }
        else {                      /** Read all the command from the input file and write the output to a out file. */
            String fileInput = args[0];
            appManager.operatorOnFileInputs(fileInput);

            /** shutdown and terminate threads */
            appManager.operate("end");
        }
    }


    /**
     * Description - operator() having this, enables us for test coverage
     */
    public void operator() {
        while (keepOperational) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nInput:\n");
            String input = scanner.nextLine();

            /** delegate the input - all for enabling unit testing */
            operate(input);
        }
    }


    public void operatorOnFileInputs(String inputFile) {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                operate(readLine);
                Thread.sleep(3000);
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                fileReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param input - input command
     * Description: Operator object to handle all of the input commands
     */
    public void operate(String input) {
        String[] values = input.split(" ");
        String command = values[0];

        /** gracefull kill, 'end' command called */
        if (command.equalsIgnoreCase("end")) {
            shutdown();
            System.exit(0);
        }

        if (!isValidInput(command)) {
            System.out.println("\nWarning: Invalid command usage: " + CommandUsage + "\n");

        } else {
            if (command.equalsIgnoreCase("park") && values.length < 3) {
                throw new ArrayIndexOutOfBoundsException();
            }
            else if (!command.equalsIgnoreCase("status") && values.length == 1) {
                System.out.println("\nWarning: Invalid arguments: " + ArgumentUsage + "\n");

            } else {
                if (command.equalsIgnoreCase("status")) {
                    ParkingLotOperatorInstance.setCommandAndArgument(command);
                } else {
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
            }
        }
    }


    /**
     * @param command   - input command
     * @return          - 'true' if valid else 'false'
     */
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


    /**
     * Description: Used by the shutdown hook which is eventually used to kill intermediately.
     * VERY IMPORTANT to have so that the thread is not left out as a demon thread.
     */
    private void shutdown() {
        //LOG.debug("# Shutting down parking facility now... ");
        ParkingLotOperatorInstance.shutdownParkingLot();

        /** Since there is a sleep of 1 at the thread, we need to wait to come out of run()
         * However, sleep at the run() is required so that the thread digests the input command */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        OperatorThread.interrupt();
    }


    /**
     * Description: Shutdown hook processor thread which ensures a graceful shutdown of the application by closing all
     * opened threads as well.
     */
    private class ProcessorHook extends Thread {
        @Override
        public void run() {
            //LOG.warn("# From shutdown-hook, force shutdown all the running threads.");
            shutdown();
        }
    }
}
