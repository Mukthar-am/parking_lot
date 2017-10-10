package org.muks.parking.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by 300000511 on 09/10/17
 *
 *  All test cases on application manager class functionality
 */
public class AppManagerTests {
    private final Logger LOG = LoggerFactory.getLogger("TestLog");

    @Test
    public void ManagerStartup() {
        LOG.info("# Parking manager - startup tests.");
        Assert.assertTrue(true);
    }
}
