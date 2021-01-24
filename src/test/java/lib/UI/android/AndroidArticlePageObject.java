package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;
import lib.UI.MainPageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    public AndroidArticlePageObject(AppiumDriver driver){
       super(driver);
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        SAVE_BUTTON = "xpath://android.widget.ImageView[@content-desc='Add this article to a reading list']";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        FOLDER_NAME = "xpath://android.widget.TextView[@text='{FOLDER}']";
        BACK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        CLOSE_BUTTON = "xpath://*[@resource-id ='org.wikipedia:id/search_toolbar']/android.widget.ImageButton";
    }
}
