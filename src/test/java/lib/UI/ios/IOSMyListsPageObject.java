package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    public IOSMyListsPageObject(RemoteWebDriver driver){
        super(driver);
        ARTICLE_NAME = "xpath://XCUIElementTypeStaticText[@name='{FOLDER}']";
    }
}
