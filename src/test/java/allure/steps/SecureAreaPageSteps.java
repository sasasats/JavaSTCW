package allure.steps;

import allure.pages.SecureAreaPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SecureAreaPageSteps {

    private final SecureAreaPage secureAreaPage;

    public SecureAreaPageSteps(WebDriver driver) {
        this.secureAreaPage = new SecureAreaPage(driver);
    }

    @Step("Check Secure Area page is displayed")
    public void checkIsDisplayed() {
        Assert.assertTrue(secureAreaPage.isDisplayed());
    }

    @Step("Check flash message with '{message}' text is displayed")
    public void checkFlashMessageDisplayed(String message) {
        Assert.assertTrue(secureAreaPage.getDataAlertText().contains(message));
    }
}
