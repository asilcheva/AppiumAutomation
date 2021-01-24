package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
        FOLDER_NAME = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER}']";
        FOLDER_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        ARTICLE_NAME = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{FOLDER}']";
    }
}
