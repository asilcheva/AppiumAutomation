package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected String FOLDER_NAME;
    protected String FOLDER_INPUT;
    protected String OK_BUTTON;
    protected String ARTICLE_NAME;
    protected String REMOVE_FROM_SAVED_BUTTON;

    private String getFolderByName(String folderName) {
        return FOLDER_NAME.replace("{FOLDER}", folderName);
    }

    private String getArticleByName(String folderName) {
        return ARTICLE_NAME.replace("{FOLDER}", folderName);
    }

    @Step("Open folder with articles")
    public void openFolderByName(String folderName) {
        this.waitForElementPresentAndClick((getFolderByName(folderName)), "Can't find necessary list", 5);
    }

    @Step("Wait for presence of article")
    public WebElement waitForArticleToAppear(String articleName) {
        String articleXpath = getArticleByName(articleName);
        return this.waitForElementPresent((articleXpath), "Can't find necessary  article", 10);
    }

    @Step("Wait for presence of article and click")
    public void waitForArticleToAppearAndClick(String articleName) {
        String articleXpath = getFolderByName(articleName);
        this.waitForElementPresent((articleXpath), "Can't find necessary  article", 10)
                .click();
    }

    @Step("Wait for absence of article")
    public boolean waitForArticleToDisAppear(String articleName) {
        String articleXpath = getArticleByName(articleName);
        return this.waitForElementNotPresent((articleXpath), "Can't find necessary  article", 10);
    }

    @Step("Create saved list and click OK button")
    public void createListAndAddArticle(String nameOfFolder) {
        this.waitForElementPresentClearAndSandKeys((FOLDER_INPUT), nameOfFolder, "Can't find text input", 5);
        this.waitForElementPresentAndClick((OK_BUTTON), "Can't find OK", 5);
    }

    @Step("Add article to folder")
    public void addArticleToFolder(String nameOfFolder) {
        this.openFolderByName(nameOfFolder);
    }

    public String getRemoveButtonByTitle(String articleName) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{FOLDER}", articleName);
    }

    @Step("Swipe article to delete")
    public void swipeArticleToDelete(String articleName) {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            waitForArticleToAppear(articleName);
            String articleXpath = getArticleByName(articleName);
            this.swipeElementToLeft((articleXpath), "Can't find an article", 5);
            if (Platform.getInstance().isIOS()) {
                this.clickElementToTheRightUpperCorner(articleXpath, "Can't find saved article");
            }
        } else {
            String removeLocator = getRemoveButtonByTitle(articleName);
            this.waitForElementPresentAndClick(removeLocator, "Can't click button to remove article from saved", 20);
            driver.navigate().refresh();
        }
    }
}
