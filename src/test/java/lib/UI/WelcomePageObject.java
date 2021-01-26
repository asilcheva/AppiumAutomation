package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {
    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia";
    private static final String STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore";
    private static final String STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages";
    private static final String STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected";
    private static final String NEXT_LINK = "id:Next";
    private static final String GET_STARTED_BUTTON = "id:Get started";
    private static final String SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";

    public void waitForLearnMoreLink() {
        this.waitForElementPresent((STEP_LEARN_MORE_LINK), "Can't find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementPresent((STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Can't find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent((STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK), "Can't find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent((STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK), "Can't find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton() {
        this.waitForElementPresent((NEXT_LINK), "Can't find 'Next' link", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementPresent((GET_STARTED_BUTTON), "Get started", 10);
    }

    public void clickSkip() {
        this.waitForElementPresentAndClick(SKIP, "Can't find and click Skip button", 5);
    }
}
