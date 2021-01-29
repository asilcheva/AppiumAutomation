package lib.UI.mobile_web;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "css:#ca-watch[title='Watch']";
        BACK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        OPTIONS_REMOVE_FROM_MYLIST_BUTTON = "css:#ca-watch[title='Unwatch']";
    }
}
