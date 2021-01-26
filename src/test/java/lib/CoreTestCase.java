package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.UI.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;
    protected Platform platform;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.platform = new Platform();
        driver = Platform.getInstance().getDriver();
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.rotateScreenPortrait();
            this.skipWelcomePageForMobileApp();
        } else {
            this.openWikiWebPageForMobileWeb();
        }
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else return;
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else return;
    }

    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else return;
    }

    private void skipWelcomePageForMobileApp() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            if (Platform.getInstance().isIOS()) {
                WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
                welcomePageObject.clickSkip();
            }
        } else return;
    }

    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        }
    }
}
