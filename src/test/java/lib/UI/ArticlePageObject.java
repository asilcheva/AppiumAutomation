package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String TITLE = "xpath://android.view.View[1]/android.view.View[1]/android.view.View[1]";
    private static final String FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
    private static final String SAVE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
    private static final String ADD_TO_LIST_BUTTON = "xpath://android.widget.Button[@text='ADD TO LIST']";
    private final static String FOLDER_NAME = "xpath://android.widget.TextView[@text='{FOLDER}']";
    private static final String BACK= "xpath://*[@resource-id = 'org.wikipedia:id/page_toolbar']/android.widget.ImageButton";
    private static final String CLOSE_BUTTON= "xpath://*[@resource-id ='org.wikipedia:id/search_toolbar']/android.widget.ImageButton";
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent((TITLE), "Can't find an article", 5);
    }

    private String getFolderByName(String folderName) {
        return FOLDER_NAME.replace("{FOLDER}", folderName);
    }
    public void openFolderByName(String folderName) {
        this.waitForElementPresentAndClick((getFolderByName(folderName)), "Can't find necessary list", 5);
    }

    public String waitAndGetArticleTitle() {
        return waitForTitleElement().getAttribute("text");
    }
    public boolean assertTitlePresent() {
        return this.assertElementPresent((TITLE));
    }

    public void swipeToFooter() {
        this.swipeUpToFindTheElement((FOOTER_ELEMENT), "Can't find footer", 5);
    }

    public void saveAndAddArticle() {
        this.waitForElementPresentAndClick((SAVE_BUTTON), "Can't find article menu button", 10);
        this.waitForElementPresentAndClick((ADD_TO_LIST_BUTTON), "", 5);
    }
    public void closeArticle() {
        this.waitForElementPresentAndClick((BACK), "Can't find Back button", 10);
    }
    public void closeSearchList() {
        this.waitForElementPresentAndClick((CLOSE_BUTTON), "Can't find Close button", 10);
    }
}
