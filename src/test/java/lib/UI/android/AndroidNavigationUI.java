package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
        MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
        VIEW_LISTS ="xpath://*[@text='VIEW LIST']";
    }
}
