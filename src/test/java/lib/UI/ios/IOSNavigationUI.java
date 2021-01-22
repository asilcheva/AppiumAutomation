package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
        MY_LISTS = "id:Saved";
    }
}
