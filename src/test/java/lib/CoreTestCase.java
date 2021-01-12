package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private String appiumURL = "http://127.0.0.1:4723/wd/hub";
    private static final String  PLATFORM_ANDROID= "android";
    private static final String  PLATFORM_IOS= "ios";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.getCapabilitiesByPlatformEnv();
        this.rotateScreenPortrait();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
    private void getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (PLATFORM_ANDROID.equals(platform)){
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("deviceName", "TestDevice");
            desiredCapabilities.setCapability("platformVersion", "11.0");
            desiredCapabilities.setCapability("automationName", "Appium");
            desiredCapabilities.setCapability("appPackage", "org.wikipedia");
            desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
            desiredCapabilities.setCapability("app", "C:\\Anutka\\ProjectsAutotest\\javaAppiumAutomation\\wikipedia.apk");
            driver = new AndroidDriver(new URL(appiumURL), desiredCapabilities);
        }
        else if (PLATFORM_IOS.equals(platform)){
            desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro Max");
            desiredCapabilities.setCapability("platformVersion", "14.3");
            desiredCapabilities.setCapability("app", "/Users/Desktop/anna/Wikipedia.app");
            driver = new IOSDriver(new URL(appiumURL), desiredCapabilities);
        }
        else throw new Exception("Can't run platform from env variable. Platform value = "+platform);
    }
}
