package org.muks.parking.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Method;

/**
 * Created by 300000511 on 12/10/17
 * Testing all the input commands.
 */
public class CommandTests {
    private final Logger LOG = LoggerFactory.getLogger("TestLog");
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    @BeforeMethod
    public void captureSysout(Method method) {
        System.out.println("\n\nBegin TestCase: " + method.getName());
        // Start capturing
        System.setOut(new PrintStream(buffer));
    }

    @AfterMethod
    public void releaseSysout(Method method) {
        // release
        buffer.reset();
        System.out.println("End TestCase: " + method.getName() + "\n\n");
    }



    @Test
    public void TestCommandCreateParkingLot() {
        // Start capturing
        System.setOut(new PrintStream(buffer));
        String expectedOutput = "Created a parking lot with 2 slots";

        String data = "create_parking_lot 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);
        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();
        actualOutput = actualOutput.replace("\n", "").replace("\r", "");
        System.out.println("Expected: " + expectedOutput + "\nActual: " + actualOutput);

        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }




    @Test
    public void TestStatusCommand() {
        String expectedOutput = "Created a parking lot with 2 slots\n" +
                "Slot No\tRegistration No.\tColor\n" +
                "1\tnull\tnull\n" +
                "2\tnull\tnull\n";

        String data = "create_parking_lot 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = "status";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        appManager.operate(data);

        // Start capturing
        System.setOut(new PrintStream(buffer));

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();
        System.out.println("Expected: " + expectedOutput + "\nActual: " + actualOutput);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(actualOutput, expectedOutput);
    }


    @Test
    public void TestAValidParkCommand() {
        String expectedOutput = "Created a parking lot with 2 slots\n" +
                "Allocated slot number: 1\n" +
                "Slot No\tRegistration No.\tColor\n" +
                "1\tka-05-ha-450\tsilver\n" +
                "2\tnull\tnull\n";

        String data = "create_parking_lot 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = "park KA-05-HA-450 silver";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        appManager.operate(data);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = "status";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        appManager.operate(data);

        // Start capturing
        System.setOut(new PrintStream(buffer));

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();

        System.out.println("Expected: " + expectedOutput + "\nActual: " + actualOutput);


        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(actualOutput, expectedOutput);
    }



    @Test
    public void TestStatusCommandWithInvalidArgs() {
        String data = "create_parking_lot 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = "park KA-05-HA-450";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        appManager.operate(data);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start capturing
        System.setOut(new PrintStream(buffer));

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();

        System.out.println("Actual: " + actualOutput);


        data = "end";
        appManager.operate(data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        if (actualOutput.contains("ArrayIndexOutOfBoundsException"))
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);

    }


}
