package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    protected String MY_LISTS;
    protected String VIEW_LISTS;
    public void clickSaved() {
        this.waitForElementPresentAndClick((MY_LISTS), "Can't find My list", 5);
    }
    public void clickViewLists(){
        this.waitForElementPresentAndClick((VIEW_LISTS), "Can't find My list", 5);
    }
}
