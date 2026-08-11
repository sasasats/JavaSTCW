package allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        WebElement header = driver.findElement(By.xpath("//body[.//*[text()='Login Page']]"));

        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .until(driver1 -> header.isDisplayed());
    }

    public void fillUsername(String username) {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
    }

    public void fillPassword(String password) {
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[.//*[contains(.,'Login')]]")).click();
    }

    public String getDataAlertText() {
        return driver.findElement(By.xpath("//*[@data-alert]")).getText();
    }
}
