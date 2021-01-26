package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.MyListsPageObject;
import lib.UI.android.AndroidMyListsPageObject;
import lib.UI.ios.IOSMyListsPageObject;
import lib.UI.mobile_web.MVMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        }
        else if (Platform.getInstance().isIOS()) {
            return new IOSMyListsPageObject(driver);
        }
        else return new MVMyListsPageObject(driver);
    }
}
