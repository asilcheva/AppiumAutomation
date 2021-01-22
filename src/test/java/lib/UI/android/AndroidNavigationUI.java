package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
        MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
        VIEW_LISTS ="xpath://android.widget.Button[@text='VIEW LIST']";
    }
}
