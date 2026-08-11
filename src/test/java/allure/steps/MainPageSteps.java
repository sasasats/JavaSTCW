package allure.steps;

import allure.pages.MainPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainPageSteps {

    private final MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
    }

    @Step("Check Main page is displayed")
    public void checkIsDisplayed() {
        Assert.assertTrue(mainPage.isDisplayed());
    }

    @Step("Go to '{linkName}' page")
    public void goToPage(String linkName) {
        mainPage.goToPage(linkName);
    }
}
