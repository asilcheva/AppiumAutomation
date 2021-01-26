package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    public IOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
        TITLE = "xpath:(//XCUIElementTypeStaticText[@name='Appium'])[1]";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_BUTTON = "id:Save for later";
        BACK = "id:Back";
        CLOSE_BUTTON = "id:places auth close";
    }
}
