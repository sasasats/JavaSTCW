package allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static allure.utils.WaitUtils.waitUntil;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        return waitUntil(driver, d ->
                d.findElement(By.xpath("//body[.//*[text()='Welcome to the-internet']]")).isDisplayed());
    }

    public void goToPage(String linkName) {
        driver.findElement(By.xpath("//a[text()='%s']".formatted(linkName))).click();
    }
}
