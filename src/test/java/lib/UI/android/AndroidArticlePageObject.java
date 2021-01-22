package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;
import lib.UI.MainPageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    public AndroidArticlePageObject(AppiumDriver driver){
       super(driver);
        TITLE = "xpath://android.view.View[1]/android.view.View[1]/android.view.View[1]";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        SAVE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
        ADD_TO_LIST_BUTTON = "xpath://android.widget.Button[@text='ADD TO LIST']";
        FOLDER_NAME = "xpath://android.widget.TextView[@text='{FOLDER}']";
        BACK = "xpath://*[@resource-id = 'org.wikipedia:id/page_toolbar']/android.widget.ImageButton";
        CLOSE_BUTTON = "xpath://*[@resource-id ='org.wikipedia:id/search_toolbar']/android.widget.ImageButton";
    }
}
