package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class KP2 {
    private static final String RESOURCES = "/home/asats/IdeaProjects/JavaSTCW/src/test/resources/";

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
//        options.addArguments("--headless");
//        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }

    @Test
    public void addCookies() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().addCookie(new Cookie("foo", "bar"));
    }

    @Test
    public void getNamedCookies() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().addCookie(new Cookie("foo", "bar"));
        Cookie cookie = driver.manage().getCookieNamed("foo");
        Assert.assertEquals(cookie.getValue(), "bar");
    }

    @Test
    public void deleteCookieNamed() {
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        driver.manage().addCookie(new Cookie("test1", "cookie1"));
        driver.manage().deleteCookieNamed("test1");
        var cookies = driver.manage().getCookies();
        Assert.assertTrue(cookies.isEmpty());
    }

    @Test
    public void testUplaod() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/upload-download.php");
        WebElement upload = driver.findElement(By.xpath("//input[@id='uploadFile']"));

        upload.sendKeys(Path.of(RESOURCES + "sampleFile.jpeg").toFile().getAbsolutePath());
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
