package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT = "xpath://*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='{sub}']";
        SEARCH_RESULT_WITH_DESCRIPTION = "xpath://android.view.ViewGroup[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and ./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]";
        CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        ALL_ARTICLES= "id:org.wikipedia:id/page_list_item_title";
        NO_RESULTS=  "xpath://android.widget.TextView[@text='No results']";
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }
}
