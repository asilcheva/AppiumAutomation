package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected String FOLDER_NAME;
    protected String FOLDER_INPUT;
    protected String OK_BUTTON;
    protected String ARTICLE_NAME;

    private String getFolderByName(String folderName) {
        return FOLDER_NAME.replace("{FOLDER}", folderName);
    }
    private String getArticleByName(String folderName) {
        return ARTICLE_NAME.replace("{FOLDER}", folderName);
    }

    public void openFolderByName(String folderName) {
        this.waitForElementPresentAndClick((getFolderByName(folderName)), "Can't find necessary list", 5);
    }

    public WebElement waitForArticleToAppear(String articleName) {
        String articleXpath = getArticleByName(articleName);
        return this.waitForElementPresent((articleXpath), "Can't find necessary  article", 10);
    }

    public void waitForArticleToAppearAndClick(String articleName) {
        String articleXpath = getFolderByName(articleName);
        this.waitForElementPresent((articleXpath), "Can't find necessary  article", 10)
                .click();
    }


    public boolean waitForArticleToDisAppear(String articleName) {
        String articleXpath = getArticleByName(articleName);
        return this.waitForElementNotPresent((articleXpath), "Can't find necessary  article", 10);
    }

    public void createListAndAddArticle(String nameOfFolder) {
        this.waitForElementPresentClearAndSandKeys((FOLDER_INPUT), nameOfFolder, "Can't find text input", 5);
        this.waitForElementPresentAndClick((OK_BUTTON), "Can't find OK", 5);
    }

    public void addArticleToFolder(String nameOfFolder) {
        this.openFolderByName(nameOfFolder);
    }

    public void swipeArticleToDelete(String articleName) {
        waitForArticleToAppear(articleName);
        String articleXpath = getArticleByName(articleName);
        this.swipeElementToLeft((articleXpath), "Can't find an article", 5);
   if (Platform.getInstance().isIOS()) {
       this.clickElementToTheRightUpperCorner(articleXpath, "Can't find saved article");
   }
    }

    public String getArticleInListTitle(String articleInListName) {
        return waitForArticleToAppear(articleInListName)
                .getAttribute("text");
    }
}
