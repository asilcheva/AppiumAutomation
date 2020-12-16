import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.function.Predicate;

public class FirstTest {
    private AppiumDriver driver;
    private static DesiredCapabilities desiredCapabilities;

    @Before
    public void setUp() throws Exception {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "TestDevice");
        desiredCapabilities.setCapability("platformVersion", "11.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "C:\\Anutka\\ProjectsAutotest\\javaAppiumAutomation\\wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
