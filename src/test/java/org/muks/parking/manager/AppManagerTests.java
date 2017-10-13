package org.muks.parking.manager;

import org.muks.parking.utils.TestResultsListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.*;

/**
 * Created by 300000511 on 09/10/17
 *
 *  All test cases on application manager class functionality
 */

@Listeners(TestResultsListener.class)
public class AppManagerTests {
    //private final Logger LOG = LoggerFactory.getLogger("TestLog");
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    @BeforeMethod
    public void captureSysout() {
        // Start capturing
        System.setOut(new PrintStream(buffer));
    }

    @AfterMethod
    public void releaseSysout() {
        // release
        buffer.reset();
    }


    @Test
    public void TestInvalidCommand() {
        String expectedOutput = "Warning: Invalid command usage: " +
                "Valid commands are: park | create_parking_lot | status | leave | registration_numbers_for_cars_with_colour | slot_number_for_registration_number | slot_numbers_for_cars_with_colour";

        String data = "parking";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();

        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        System.out.println("Expected: " + expectedOutput + "\nActual: " + actualOutput);

//        data = "end";
//        appManager.operate(data);
//        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }



    @Test
    public void InvalidCommandUnlistedCmd() {
        String expectedOutput = "Warning: Invalid command usage: " +
                "Valid commands are: park | create_parking_lot | status | leave | registration_numbers_for_cars_with_colour | slot_number_for_registration_number | slot_numbers_for_cars_with_colour";

        String data = "unlisted";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);
        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        System.out.println("Expected: " + expectedOutput + "\nActual: " + actualOutput);

        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
