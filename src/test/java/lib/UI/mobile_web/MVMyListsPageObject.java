package lib.UI.mobile_web;

import lib.UI.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVMyListsPageObject extends MyListsPageObject {
    public MVMyListsPageObject(RemoteWebDriver driver){
        super(driver);
        FOLDER_NAME = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER}']";
        FOLDER_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        ARTICLE_NAME = "xpath://h3[contains(text(), '{FOLDER}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[@title = '{FOLDER}']/a[@class='mw-ui-icon mw-ui-icon-wikimedia-unStar-progressive mw-ui-icon-element   watch-this-article watched']";
    }
}
