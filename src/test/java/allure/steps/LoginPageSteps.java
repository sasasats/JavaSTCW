package allure.steps;

import allure.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageSteps {

    private final LoginPage loginPage;

    public LoginPageSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    @Step("Check Login page is displayed")
    public void checkIsDisplayed() {
        Assert.assertTrue(loginPage.isDisplayed());
    }

    @Step("Login with '{username}' username")
    public void login(String username, String password) {
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Check flash message with '{message}' text is displayed")
    public void checkFlashMessageDisplayed(String message) {
        Assert.assertTrue(loginPage.getDataAlertText().contains(message));
    }
}
