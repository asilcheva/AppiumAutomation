package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    protected String MY_LISTS;
    protected String VIEW_LISTS;
    protected String OPEN_NAVIGATION;

    public void clickSaved() {
        if (Platform.getInstance().isMW()) {
            this.tryToClickElementWithFewAttempts(MY_LISTS, "Can't find My list", 10);
        } else {
            this.waitForElementPresentAndClickable((MY_LISTS), "Can't find My list", 5);
            this.waitForElementPresentAndClick((MY_LISTS), "Can't find My list", 5);
        }
    }

    public void clickViewLists() {
        this.waitForElementPresentAndClick((VIEW_LISTS), "Can't find My list", 5);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementPresentAndClick(OPEN_NAVIGATION, "Can't find navigation button", 5);
        } else return;
    }
}
