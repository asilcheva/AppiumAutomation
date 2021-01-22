package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    public IOSMyListsPageObject(AppiumDriver driver){
        super(driver);
        FOLDER_NAME = "xpath://XCUIElementTypeStaticText[@name='{FOLDER}']";
    }
}
