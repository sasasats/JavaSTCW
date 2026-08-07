package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class KP1 {
    private static final String DOWNLOADS = "/home/asats/IdeaProjects/JavaSTCW/src/downloads/";

    private WebDriver driver;

    @BeforeMethod
    public void createDownloadFolder() throws IOException {
        Files.createDirectories(Path.of(DOWNLOADS));
    }

    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("prefs", getDefaultChromeOptions());
        driver = new ChromeDriver(options);
    }

    @Test
    public void testNavigation() {
        driver.get("https://www.google.com/");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
    }

    @Test
    public void testIframe() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/frames.php");
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@width,'100')]"));
        driver.switchTo().frame(iframe);
        driver.switchTo().defaultContent();
    }

    @Test
    public void testAlert() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");
        WebElement btnAlert = driver.findElement(By.xpath("//button[@onclick='showAlert()']"));
        btnAlert.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Hello world!", "Text did not match");
        alert.accept();
    }

    @Test
    public void testAlertConfirm() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");
        WebElement btnAlert = driver.findElement(By.xpath("//button[@onclick='myDesk()']"));
        btnAlert.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Press a button!", "Text did not match");
        alert.accept();

        WebElement result = driver.findElement(By.xpath("//*[@id='desk']"));
        Assert.assertEquals(result.getText(), "You pressed OK!");
    }

    @Test
    public void testAlertCancel() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");
        WebElement btnAlert = driver.findElement(By.xpath("//button[@onclick='myDesk()']"));
        btnAlert.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Press a button!", "Text did not match");
        alert.dismiss();

        WebElement result = driver.findElement(By.xpath("//*[@id='desk']"));
        Assert.assertEquals(result.getText(), "You pressed Cancel!");
    }

    @Test
    public void testAlertPrompt() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");
        WebElement btnAlert = driver.findElement(By.xpath("//button[@onclick='myPromp()']"));
        btnAlert.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "What is your name?", "Text did not match");
        alert.sendKeys("Andrey");
        alert.accept();
    }

    @Test
    public void testNewTab() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");
        WebElement btnNewTab = driver.findElement(By.xpath("//button[@title='New Tab']"));
        btnNewTab.click();

        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        WebElement lblNewTab = driver.findElement(By.xpath("//h1[text()='New Tab']"));
        Assert.assertTrue(lblNewTab.isDisplayed());

        driver.close();
        driver.switchTo().window((String) windowHandles[0]);

        Assert.assertTrue(btnNewTab.isDisplayed());
    }

    @Test
    public void testNewWindow() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");
        WebElement btnNewWindow = driver.findElement(By.xpath("//button[text()='New Window']"));
        btnNewWindow.click();

        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        WebElement lblNewWindow = driver.findElement(By.xpath("//h1[text()='New Window']"));
        List<WebElement> elementList = driver.findElements(By.xpath("//button[text()='New Window']"));
        Assert.assertTrue(elementList.isEmpty());
        Assert.assertTrue(lblNewWindow.isDisplayed());

        driver.close();
        driver.switchTo().window((String) windowHandles[0]);

        Assert.assertTrue(btnNewWindow.isDisplayed());
    }

    @Test
    public void testDownload() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/upload-download.php");
        WebElement btnDownload = driver.findElement(By.xpath("//a[@id='downloadButton']"));
        btnDownload.click();

        Path expectedFile = Path.of(DOWNLOADS + "/sampleFile.jpeg");
        boolean isDownloaded = new FluentWait<>((Void) null)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(v -> Files.exists(expectedFile));

        Assert.assertTrue(isDownloaded);
    }

    @Test
    public void testWindowSize() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/selenium/practice/upload-download.php");

        Thread.sleep(5000);
        driver.manage().window().minimize();
        Thread.sleep(5000);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.manage().window().fullscreen();
        Thread.sleep(5000);
        driver.manage().window().setSize(new Dimension(800, 600));
        Thread.sleep(5000);
    }

    @Test
    public void testBasicAuthLegacy() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/");
        WebElement lnkBasicAuth = driver.findElement(By.xpath("//*[text()='Basic Auth']"));
        lnkBasicAuth.click();

        WebElement element = driver.findElement(By.xpath("//h3[text()='Basic Auth']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testBasicAuthCurrent() {
        HasAuthentication authentication = (HasAuthentication) driver;
        authentication.register(() -> new UsernameAndPassword("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com/");
        WebElement lnkBasicAuth = driver.findElement(By.xpath("//*[text()='Basic Auth']"));
        lnkBasicAuth.click();

        WebElement element = driver.findElement(By.xpath("//h3[text()='Basic Auth']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testJsClick() {
        HasAuthentication authentication = (HasAuthentication) driver;
        authentication.register(() -> new UsernameAndPassword("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com/");
        WebElement lnkBasicAuth = driver.findElement(By.xpath("//*[text()='Basic Auth']"));
        jsClick(lnkBasicAuth);


        WebElement element = driver.findElement(By.xpath("//h3[text()='Basic Auth']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testAttribute() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement element = driver.findElement(By.xpath("//a[text()='Elemental Selenium']"));

        Assert.assertEquals(element.getAttribute("target"), "_blank");
    }

    @Test
    public void testImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testExplicitWait() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement element = driver.findElement(By.xpath("//a[text()='Elemental Selenium']"));

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean result = wait.until(d -> element.isDisplayed());
        Assert.assertTrue(result);
    }

    @Test
    public void testFluentWait() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement element = driver.findElement(By.xpath("//a[text()='Elemental Selenium']"));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250));

        boolean result = wait.until(d -> element.isDisplayed());
        Assert.assertTrue(result);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @AfterMethod
    public void deleteDownloads() throws IOException {
        deleteDirectoryRecursively(Path.of(DOWNLOADS));
    }

    private Map<String, Object> getDefaultChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", DOWNLOADS);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        return prefs;
    }

    private void deleteDirectoryRecursively(Path path) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder()) // сначала файлы, потом родительские папки
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
