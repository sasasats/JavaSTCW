package testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG {

    @Test
    public void testEquals() {
        String actual = "Dashboard";
        String expected = "Dashboard";

        Assert.assertEquals(actual, expected, "%s and %s are not equals".formatted(actual, expected));
    }

    @Test
    public void testTrue() {
        boolean actual = true;

        Assert.assertTrue(actual, "%s is not true".formatted(actual));
    }

    @Test
    public void testSoft() {
        boolean actual = true;

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertFalse(actual, "%s is not false".formatted(actual));
        softAssert.assertTrue(actual, "%s is not true".formatted(actual));
//        softAssert.assertFalse(actual, "%s is not false".formatted(actual));
        softAssert.assertAll();
    }

    @Test(enabled = false)
    public void ignoredTest() {
        boolean actual = true;

        Assert.assertFalse(actual, "%s is not false".formatted(actual));
    }

    @Test(groups = "wip")
    public void groupTest() {
        boolean actual = true;

        Assert.assertTrue(actual, "%s is not true".formatted(actual));
    }
}
