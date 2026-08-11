package allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        WebElement header = driver.findElement(
                By.xpath("//body[.//*[text()='Welcome to the-internet']]"));

        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .until(driver1 -> header.isDisplayed());
    }

    public void goToPage(String linkName) {
        driver.findElement(By.xpath("//a[text()='%s']".formatted(linkName))).click();
    }
}
