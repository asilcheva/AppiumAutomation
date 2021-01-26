package lib.UI.mobile_web;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
        SEARCH_INIT_ELEMENT = "xpath://input[@placeholder='Search Wikipedia']";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT = "xpath://li[@title='{substring}']";
        SEARCH_RESULT_WITH_DESCRIPTION ="xpath://li[@title = '{TITLE}']/a/div[contains(text(), '{DESCRIPTION}')]";
        CANCEL_BUTTON = "css:button.cancel";
        ALL_ARTICLES = "css:up.page-list thumbs actionable>li.page-summary";
        NO_RESULTS = "css:p.without-results";
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }
}
