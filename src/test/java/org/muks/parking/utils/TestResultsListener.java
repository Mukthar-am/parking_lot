package org.muks.parking.utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestResultsListener extends TestListenerAdapter {
    //private final Logger LOG = LoggerFactory.getLogger("TestLog");
    ITestResult iTestResult;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + ": Failed");
        System.out.println(result.getMethod().getMethodName() + ": Failed");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + ": Pass");
        System.out.println(result.getMethod().getMethodName() + ": Pass");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + ": Skipped");
        System.out.println(result.getMethod().getMethodName() + ": Skipped");
    }

}
