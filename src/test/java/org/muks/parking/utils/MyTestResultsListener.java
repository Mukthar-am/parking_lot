package org.muks.parking.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class MyTestResultsListener extends TestListenerAdapter {
    private final Logger LOG = LoggerFactory.getLogger("TestLog");
    ITestResult iTestResult;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + ": Failed");
        LOG.info(result.getMethod().getMethodName() + ": Failed");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + ": Pass");
        LOG.info(result.getMethod().getMethodName() + ": Pass");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + ": Skipped");
        LOG.info(result.getMethod().getMethodName() + ": Skipped");
    }

}
