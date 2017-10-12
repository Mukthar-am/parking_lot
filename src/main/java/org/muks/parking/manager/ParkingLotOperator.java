package org.muks.parking.manager;

import org.muks.parking.space.ParkingLot;
import org.muks.parking.utils.CommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 300000511 on 11/10/17
 */

public class ParkingLotOperator implements Runnable {
    private static Logger LOG = LoggerFactory.getLogger(ParkingLotOperator.class);
    private boolean OperateParkingLot;  /** Is false by default */
    private String Command = null;
    private String[] Argument = null;
    private boolean OperationCompleted = false;
    private CommandExecutor CmdExecutor = new CommandExecutor();

    public void startParkingLot() { this.OperateParkingLot = true; }

    public void shutdownParkingLot() { this.OperateParkingLot = false; }

    private void healthCheck() { LOG.debug("Parking lot is now open..."); }

    public void setCommandAndArgument(String cmd, String[] args) {
        this.Command = cmd;
        this.Argument = args;
    }

    public void setCommandAndArgument(String cmd) {
        this.Command = cmd;
    }

    public boolean isOperationCompleted() { return this.OperationCompleted; }




    @Override
    public void run() {

        while (OperateParkingLot) {
            /** Wait for the input */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.Command != null) {
                this.OperationCompleted = false;
                try {
                    /** parking lot handler logic */
                    CmdExecutor.execute(this.Command, this.Argument);

                    this.Command = null;
                    this.Argument = null;
                    this.OperationCompleted = true;

                } catch (Exception e) {
                    LOG.error("Exception: ", e);
                }
            }
        }
    }
}
