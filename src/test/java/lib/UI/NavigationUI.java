package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    private final static String MY_LISTS = "//android.widget.FrameLayout[@content-desc='My lists']";
    private final static String VIEW_LISTS ="//android.widget.Button[@text='VIEW LIST']";
    public void clickMyLists() {
        this.waitForElementPresentAndClick(By.xpath(MY_LISTS), "Can't find My list", 5);
    }
    public void clickViewLists(){
        this.waitForElementPresentAndClick(By.xpath(VIEW_LISTS), "Can't find My list", 5);
    }
}
