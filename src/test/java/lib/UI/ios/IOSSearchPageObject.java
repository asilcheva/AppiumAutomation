package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class  IOSSearchPageObject extends SearchPageObject {
    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name ='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        SEARCH_RESULT = "xpath://XCUIElementTypeStaticText[contains(@name, '{substring}')]";
        SEARCH_RESULT_WITH_DESCRIPTION ="xpath://XCUIElementTypeOther[./XCUIElementTypeStaticText[@name='{TITLE}']/following-sibling::XCUIElementTypeStaticText[@value ='{DESCRIPTION}']]";
        CANCEL_BUTTON = "id:Close";
        ALL_ARTICLES = "xpath://XCUIElementTypeOther/XCUIElementTypeStaticText";
        NO_RESULTS = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }
}
