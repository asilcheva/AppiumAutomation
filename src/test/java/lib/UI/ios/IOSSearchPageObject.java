package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    String SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
    String SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
    String SEARCH_RESULT = "xpath://*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='{substring}']";
    String SEARCH_RESULT_WITH_DESCRIPTION = "xpath://android.view.ViewGroup[./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and ./android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]";
    String CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    String ALL_ARTICLES = "id:org.wikipedia:id/page_list_item_title";
    String NO_RESULTS = "xpath://android.widget.TextView[@text='No results']";
    String SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
}

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
