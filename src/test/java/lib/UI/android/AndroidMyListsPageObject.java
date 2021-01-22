package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
        FOLDER_NAME = "xpath://android.widget.TextView[@text='{FOLDER}']";
        FOLDER_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        CREATE_NEW_BUTTON = "xpath://android.widget.TextView[@text='Create new']";
    }
}
