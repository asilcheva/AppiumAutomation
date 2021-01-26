package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT = "xpath://*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='{substring}']";
        SEARCH_RESULT_WITH_DESCRIPTION = "xpath://android.widget.LinearLayout[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and ./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]";
        CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        ALL_ARTICLES= "id:org.wikipedia:id/page_list_item_title";
        NO_RESULTS=  "xpath://android.widget.TextView[@text='No results found']";
        EMPTY_RESULTS = "id:org.wikipedia:id/search_empty_message";
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }
}
