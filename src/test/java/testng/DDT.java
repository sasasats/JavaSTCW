package testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"validUserName", "validPassword", true},
                {"validUserName", "invalidPassword", false},
                {"invalidUserName", "validPassword", false},
                {"invalidUserName", "invalidPassword", false},
        };
    }

    @Test(dataProvider = "loginData")
    public void testDataProvider(String userName, String password, boolean expectedResult) {
        boolean actualResult = performLogin(userName, password);

        Assert.assertEquals(actualResult, expectedResult, "Login was incorrect for user %s".formatted(userName));
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
    public void testDataProviderClass(String userName, String password, boolean expectedResult) {
        boolean actualResult = performLogin(userName, password);

        Assert.assertEquals(actualResult, expectedResult, "Login was incorrect for user %s".formatted(userName));
    }

    private boolean performLogin(String userName, String password) {
        return !(userName.contains("invalid") || password.contains("invalid"));
    }
}
