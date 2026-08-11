package allure;

import allure.steps.LoginPageSteps;
import allure.steps.MainPageSteps;
import allure.steps.SecureAreaPageSteps;
import allure.utils.AllureEnvironment;
import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class AllureTests {
    private static final String BASE_URL = "https://the-internet.herokuapp.com/";

    private WebDriver driver;

    @BeforeSuite
    public void generateAllureEnvironment() throws IOException {
        AllureEnvironment.generate();
    }

    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        Allure.step("Go to %s url".formatted(BASE_URL), step -> {
            driver.get(BASE_URL);
        });
    }

    @Test
    @Description("This test attempts to check that with incorrect credentials the right error message displayed")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Andrey Sats")
    @Link(name = "the-internet site", url = "https://the-internet.herokuapp.com/")
    @TmsLink("TMS-001")
    @Parameters({"Username", "Password"})
    public void testWrongAuthentication(String username, String password) {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        mainPageSteps.checkIsDisplayed();
        mainPageSteps.goToPage("Form Authentication");

        LoginPageSteps loginPageSteps = new LoginPageSteps(driver);
        loginPageSteps.checkIsDisplayed();
        loginPageSteps.login(username, password);
        loginPageSteps.checkFlashMessageDisplayed("Your username is invalid!");
    }

    @Test
    @Description("This test attempts to check that with correct credentials the right success message displayed")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Andrey Sats")
    @Link(name = "the-internet site", url = "https://the-internet.herokuapp.com/")
    @TmsLink("TMS-002")
    public void testCorrectAuthentication() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        mainPageSteps.checkIsDisplayed();
        mainPageSteps.goToPage("Form Authentication");

        LoginPageSteps loginPageSteps = new LoginPageSteps(driver);
        loginPageSteps.checkIsDisplayed();
        loginPageSteps.login("tomsmith", "SuperSecretPassword!");

        SecureAreaPageSteps secureAreaPageSteps = new SecureAreaPageSteps(driver);
        secureAreaPageSteps.checkIsDisplayed();
        secureAreaPageSteps.checkFlashMessageDisplayed("You logged into a secure area!");
    }

    @Test
    @Description("This test demonstrate failing test")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Andrey Sats")
    @Link(name = "the-internet site", url = "https://the-internet.herokuapp.com/")
    @Issue("AUTH-001")
    @TmsLink("TMS-003")
    @Parameters({"Username", "Password"})
    public void testDemoAuthenticationFailure(String username, String password) {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        mainPageSteps.checkIsDisplayed();
        mainPageSteps.goToPage("Form Authentication");

        LoginPageSteps loginPageSteps = new LoginPageSteps(driver);
        loginPageSteps.checkIsDisplayed();
        loginPageSteps.login(username, password);
        loginPageSteps.checkFlashMessageDisplayed("Your username is valid!");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            addPageSource();
            addScreenshot();
        }

        driver.quit();
    }

    private void addPageSource() {
        String pageSource = driver.getPageSource();

        if (pageSource != null) {
            Allure.addAttachment(
                    "Page source",
                    "text/html",
                    pageSource
            );
        }
    }

    private void addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(
                "Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                "png"
        );
    }
}
