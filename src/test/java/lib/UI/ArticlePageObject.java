package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleniumhq.jetty9.servlet.listener.ELContextCleaner;

abstract public class ArticlePageObject extends MainPageObject{
    protected String TITLE;
    protected String FOOTER_ELEMENT;
    protected String SAVE_BUTTON;
    protected String GOT_IT_BUTTON;
    protected String FOLDER_NAME;
    protected String BACK;
    protected String CLOSE_BUTTON;
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
        if (Platform.getInstance().isAndroid()) {
            return waitForTitleElement().getAttribute("text");
        }
        else return waitForTitleElement().getAttribute("name");
    }
    public boolean assertTitlePresent() {
        this.waitForTitleElement();
        return this.assertElementPresent((TITLE));
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindTheElement((FOOTER_ELEMENT), "Can't find footer", 5);}
        else {this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Can't find footer", 10);}
    }

    public void saveAndAddArticle() {
        this.waitForElementPresentAndClick((SAVE_BUTTON), "Can't find article menu button", 10);
        this.waitForElementPresentAndClick((GOT_IT_BUTTON), "", 10);
    }
    public void closeArticle() {
        this.waitForElementPresentAndClick((BACK), "Can't find Back button", 10);
    }
    public void clickClose() {
        this.waitForElementPresentAndClick((CLOSE_BUTTON), "Can't find Close button", 3);
        this.waitForElementNotPresent(CLOSE_BUTTON, "Prompt is still here", 3);
    }
    public void addArticleToMySaved(){
        this.waitForElementPresentAndClick(SAVE_BUTTON, "Can't find add to list button", 3);
    }
}
