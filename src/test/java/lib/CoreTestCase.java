package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.UI.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {
    protected RemoteWebDriver driver;
    protected Platform platform;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        this.platform = new Platform();
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.rotateScreenPortrait();
            this.skipWelcomePageForMobileApp();
        } else {
            this.openWikiWebPageForMobileWeb();
        }
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else return;
    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else return;
    }

    @Step("Send mobile web to background")
    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else return;
    }

    @Step("Skip welcome page for IOS and Android")
    private void skipWelcomePageForMobileApp() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            if (Platform.getInstance().isIOS()) {
                WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
                welcomePageObject.clickSkip();
            }
        } else return;
    }

    @Step("Open Wiki page for mobile web")
    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try{
            Properties properties = new Properties();
            FileOutputStream fileOutputStream = new FileOutputStream("C:/Anutka/ProjectsAutotest/javaAppiumAutomation/environment.properties");
            properties.setProperty("Environment", Platform.getInstance().getPlatform());
            properties.store(fileOutputStream, "Environment");
            fileOutputStream.close();
        }
        catch (Exception e) {
            System.out.println("IO problem when writing allure properties file"+e.getMessage());
            e.printStackTrace();
        }
    }
}
