package lib.UI.mobile_web;

import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
        MY_LISTS = "css:a[data-event-name ='menu.unStar']";
        OPEN_NAVIGATION = "css:label[title='Open main menu']";
    }
}
