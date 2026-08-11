package allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static allure.utils.WaitUtils.waitUntil;

public class SecureAreaPage {
    private final WebDriver driver;

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        return waitUntil(driver, d ->
                d.findElement(By.xpath("//body[.//*[contains(.,'Secure Area')]]")).isDisplayed());
    }

    public String getDataAlertText() {
        WebElement dataAlert = waitUntil(driver, d -> d.findElement(By.xpath("//*[@data-alert]")));
        return dataAlert.getText();
    }
}
