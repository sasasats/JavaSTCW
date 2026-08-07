package testng;

import org.testng.annotations.*;

public class PrePostConditions {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class");
    }

    @BeforeGroups("wip1")
    public void beforeGroups() {
        System.out.println("Before groups");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before method");
    }

    @Test(groups = "wip1")
    public void testPrePostConditions() {
        System.out.println("Test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After method");
    }

    @AfterGroups("wip1")
    public void afterGroups() {
        System.out.println("After groups");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After suite");
    }
}
