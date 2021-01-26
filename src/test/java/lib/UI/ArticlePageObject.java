package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.seleniumhq.jetty9.servlet.listener.ELContextCleaner;

abstract public class ArticlePageObject extends MainPageObject {
    protected String TITLE;
    protected String FOOTER_ELEMENT;
    protected String SAVE_BUTTON;
    protected String GOT_IT_BUTTON;
    protected String FOLDER_NAME;
    protected String BACK;
    protected String CLOSE_BUTTON;
    protected String OPTIONS_REMOVE_FROM_MYLIST_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
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
        } else if (Platform.getInstance().isIOS()) {
            return waitForTitleElement().getAttribute("name");
        } else return waitForTitleElement().getText();
    }

    public boolean assertTitlePresent() {
        this.waitForTitleElement();
        return this.assertElementPresent((TITLE));
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindTheElement((FOOTER_ELEMENT), "Can't find footer", 5);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Can't find footer", 10);
        } else {
            scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Can't find end of article", 40);
        }
    }

    public void saveAndAddArticle() {
        this.waitForElementPresentAndClick((SAVE_BUTTON), "Can't find article menu button", 10);
        this.waitForElementPresentAndClick((GOT_IT_BUTTON), "", 10);
    }

    public void closeArticle() {
        this.waitForElementPresentAndClick((BACK), "Can't find Back button", 10);
    }

    public void clickClose() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementPresentAndClick((CLOSE_BUTTON), "Can't find Close button", 3);
            this.waitForElementNotPresent(CLOSE_BUTTON, "Prompt is still here", 3);
        } else return;
    }

    public void addArticleToMySaved() throws InterruptedException {
        if (Platform.getInstance().isMW()) {
            Thread.sleep(500);
            this.removeArticleFromSavedIfItAdded();
        }
        Thread.sleep(500);
        this.waitForElementPresentAndClickable(SAVE_BUTTON, "Can't find add to list button", 20);
        this.waitForElementPresentAndClick(SAVE_BUTTON, "Can't find add to list button", 20);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MYLIST_BUTTON)) {
            this.waitForElementPresentAndClick(OPTIONS_REMOVE_FROM_MYLIST_BUTTON, "Can't click button remove", 5);
        }
    }
}
