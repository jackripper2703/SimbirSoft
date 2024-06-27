package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

abstract public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        BasePage.setDriver(driver);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
