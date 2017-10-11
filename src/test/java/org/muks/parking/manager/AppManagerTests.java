package org.muks.parking.manager;

import org.muks.parking.utils.MyTestResultsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

/**
 * Created by 300000511 on 09/10/17
 *
 *  All test cases on application manager class functionality
 */

@Listeners(MyTestResultsListener.class)
public class AppManagerTests {
    private final Logger LOG = LoggerFactory.getLogger("TestLog");


    @Test
    public void TestInvalidCommand() {
        String expectedOutput = "Warning: Invalid command usage: " +
                "Valid commands are: park | create_parking_lot | status | leave | registration_numbers_for_cars_with_colour | slot_number_for_registration_number | slot_numbers_for_cars_with_colour";

        String data = "parking";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        String actualOutput = appManager.operate(data);
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        LOG.info("Expected: " + expectedOutput + "\nActual: " + actualOutput);

        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void TestValidParkCommand() {
        String expectedOutput = "| Successfully complete the operation: \"park\" |";

        String data = "park ka-05-ha-540";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        String actualOutput = appManager.operate(data);
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        LOG.info("Expected: " + expectedOutput + "\nActual: " + actualOutput);

        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void TestStatusCommand() {
        String expectedOutput = "| Successfully complete the operation: \"status\" |";

        String data = "status";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        String actualOutput = appManager.operate(data);
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        LOG.info("Expected: " + expectedOutput + "\nActual: " + actualOutput);


        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void TestStatusCommandWithInvalidArgs() {
        LOG.info("# Test: ValidCommandWhileStartup");
        String expectedOutput = "| Successfully complete the operation: \"status\" |";

        String data = "status ka-05-ha-540";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        String actualOutput = appManager.operate(data);
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        LOG.info("Expected: " + expectedOutput + "\nActual: " + actualOutput);


        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void InvalidCommandUnlistedCmd() {
        String expectedOutput = "Warning: Invalid command usage: " +
                "Valid commands are: park | create_parking_lot | status | leave | registration_numbers_for_cars_with_colour | slot_number_for_registration_number | slot_numbers_for_cars_with_colour";

        String data = "unlisted";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        String actualOutput = appManager.operate(data);
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        LOG.info("Expected: " + expectedOutput + "\nActual: " + actualOutput);

        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
