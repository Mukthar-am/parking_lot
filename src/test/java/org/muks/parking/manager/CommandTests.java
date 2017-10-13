package org.muks.parking.manager;

import org.testng.Assert;
import org.testng.TestException;
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
//    private final Logger LOG = LoggerFactory.getLogger("TestLog");
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
    public void TestAValidParkCommand() {
        String expectedOutput = "Created a parking lot with 2 slots\n" +
                "Allocated slot number: 1\n" +
                "Slot No\tRegistration No.\tColor\n" +
                "1\tKA-05-HA-450\tsilver\n";

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

        Assert.assertEquals(actualOutput, expectedOutput);
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

//        data = "end";
//        appManager.operate(data);
//        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(expectedOutput, actualOutput);
    }




    @Test
    public void TestStatusCommand() {
        String expectedOutput = "Created a parking lot with 2 slots\n" +
                "Slot No\tRegistration No.\tColor\n";

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

//        data = "end";
//        appManager.operate(data);
//        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Assert.assertEquals(actualOutput, expectedOutput);
    }


    /**
     * Throw a right exception and catch for right test execution
     */
    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void TestStatusCommandWithInvalidArgs() {
        String data = "create_parking_lot 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        AppManager appManager = new AppManager();
        appManager.operate(data);

        try {
            Thread.sleep(2000);
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

        if (actualOutput.contains("Created a parking lot with 2 slots"))
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);
    }


    @Test
    public void TestCommandsReadFromFileInput() {
        String expectedOutput = "Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Allocated slot number: 4\n" +
                "Allocated slot number: 5\n" +
                "Allocated slot number: 6\n" +
                "Slot number 4 is free\n" +
                "Slot No\tRegistration No.\tColor\n" +
                "1\tKA-01-HH-1234\tWhite\n" +
                "2\tKA-01-HH-9999\tWhite\n" +
                "3\tKA-01-BB-0001\tBlack\n" +
                "5\tKA-01-HH-2701\tBlue\n" +
                "6\tKA-01-HH-3141\tBlack\n" +
                "Allocated slot number: 4\n" +
                "Sorry, parking lot is full\n" +
                "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n" +
                "1, 2, 4\n" +
                "6\n" +
                "Not found\n";

        String inputFile = "/file_inputs.txt";
        File commandsInFile = new File(this.getClass().getResource(inputFile).getFile());

        // Start capturing
        System.setOut(new PrintStream(buffer));

        AppManager appManager = new AppManager();
        appManager.operatorOnFileInputs(commandsInFile.getAbsolutePath());

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String actualOutput = buffer.toString();

        System.out.println("Expected: " + expectedOutput + "\nActual: " + actualOutput);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Assert.assertEquals(actualOutput, expectedOutput);
    }

}
