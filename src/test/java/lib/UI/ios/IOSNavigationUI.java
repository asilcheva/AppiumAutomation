package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
        MY_LISTS = "id:Saved";
    }
}
