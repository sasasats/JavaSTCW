package allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static allure.utils.WaitUtils.waitUntil;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        return waitUntil(driver, d ->
                d.findElement(By.xpath("//body[.//*[text()='Login Page']]")).isDisplayed());
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
        WebElement dataAlert = waitUntil(driver, d -> d.findElement(By.xpath("//*[@data-alert]")));
        return dataAlert.getText();
    }
}
