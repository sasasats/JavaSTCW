package allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class SecureAreaPage {
    private final WebDriver driver;

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        WebElement header = driver.findElement(By.xpath("//body[.//*[contains(.,'Secure Area')]]"));

        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .until(driver1 -> header.isDisplayed());
    }

    public String getDataAlertText() {
        return driver.findElement(By.xpath("//*[@data-alert]")).getText();
    }
}
