package com.revature.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

// Example for how to run mnv test for additional test suites (mysecondsuite.xml)
public class MyFakeTests {
    @Test
    public void fakeTestCase() {
        System.out.println("This is a fake test to demonstrate \nhow to utilize second test suite");
        Assert.assertTrue(true);
    }
}
