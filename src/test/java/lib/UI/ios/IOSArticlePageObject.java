package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_TO_LIST_BUTTON = "id:Save for later";
        BACK = "id:Back";
        CLOSE_BUTTON = "id:places auth close";
    }
}
