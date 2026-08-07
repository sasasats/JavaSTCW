package testng;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][] {
                {"validUserName", "validPassword", true},
                {"validUserName", "invalidPassword", false},
                {"invalidUserName", "validPassword", false},
                {"invalidUserName", "invalidPassword", false},
        };
    }
}
