package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String TITLE = "//android.view.View[1]/android.view.View[1]/android.view.View[1]";
    private static final String FOOTER_ELEMENT = "//*[@text='View article in browser']";
    private static final String SAVE_BUTTON = "org.wikipedia:id/article_menu_bookmark";
    private static final String ADD_TO_LIST_BUTTON = "//android.widget.Button[@text='ADD TO LIST']";
    private final static String FOLDER_NAME = "//android.widget.TextView[@text='{FOLDER}']";
    private static final String BACK= "//*[@resource-id = 'org.wikipedia:id/page_toolbar']/android.widget.ImageButton";
    private static final String CLOSE_BUTTON= "//*[@resource-id ='org.wikipedia:id/search_toolbar']/android.widget.ImageButton";
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Can't find an article", 5);
    }

    private String getFolderByName(String folderName) {
        return FOLDER_NAME.replace("{FOLDER}", folderName);
    }
    public void openFolderByName(String folderName) {
        this.waitForElementPresentAndClick(By.xpath(getFolderByName(folderName)), "Can't find necessary list", 5);
    }

    public String waitAndGetArticleTitle() {
        return waitForTitleElement().getAttribute("text");
    }
    public boolean assertTitlePresent() {
        return this.assertElementPresent(By.xpath(TITLE));
    }

    public void swipeToFooter() {
        this.swipeUpToFindTheElement(By.xpath(FOOTER_ELEMENT), "Can't find footer", 5);
    }

    public void saveAndAddArticle() {
        this.waitForElementPresentAndClick(By.id(SAVE_BUTTON), "Can't find article menu button", 10);
        this.waitForElementPresentAndClick(By.xpath(ADD_TO_LIST_BUTTON), "", 5);
    }
    public void closeArticle() {
        this.waitForElementPresentAndClick(By.xpath(BACK), "Can't find Back button", 10);
    }
    public void closeSearchList() {
        this.waitForElementPresentAndClick(By.xpath(CLOSE_BUTTON), "Can't find Close button", 10);
    }
}
