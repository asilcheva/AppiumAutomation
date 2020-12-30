package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private final static String FOLDER_NAME = "//android.widget.TextView[@text='{FOLDER}']";
    private static final String FOLDER_INPUT = "org.wikipedia:id/text_input";
    private static final String OK_BUTTON = "android:id/button1";
    private static final String CREATE_NEW_BUTTON = "//android.widget.TextView[@text='Create new']";

    private String getFolderByName(String folderName) {
        return FOLDER_NAME.replace("{FOLDER}", folderName);
    }

    public void openFolderByName(String folderName) {
        this.waitForElementPresentAndClick(By.xpath(getFolderByName(folderName)), "Can't find necessary list", 5);
    }

    public WebElement waitForArticleToAppear(String articleName) {
        String articleXpath = getFolderByName(articleName);
        return this.waitForElementPresent(By.xpath(articleXpath), "Can't find necessary  article", 10);
    }

    public void waitForArticleToAppearAndClick(String articleName) {
        String articleXpath = getFolderByName(articleName);
        this.waitForElementPresent(By.xpath(articleXpath), "Can't find necessary  article", 10)
                .click();
    }


    public boolean waitForArticleToDisAppear(String articleName) {
        String articleXpath = getFolderByName(articleName);
        return this.waitForElementNotPresent(By.xpath(articleXpath), "Can't find necessary  article", 10);
    }

    public void createListAndAddArticle(String nameOfFolder) {
        this.waitForElementPresentAndClick(By.xpath(CREATE_NEW_BUTTON), "Can't find Create new link", 10);
        this.waitForElementPresentAndSandKeys(By.id(FOLDER_INPUT), nameOfFolder, "Can't find text input", 5);
        this.waitForElementPresentAndClick(By.id(OK_BUTTON), "Can't find OK", 5);
    }

    public void addArticleToFolder(String nameOfFolder) {
        this.openFolderByName(nameOfFolder);
    }

    public void swipeArticleToDelete(String articleName) {
        waitForArticleToAppear(articleName);
        String articleXpath = getFolderByName(articleName);
        this.swipeElementToLeft(By.xpath(articleXpath), "Can't find an article", 5);
    }

    public String getArticleInListTitle(String articleInListName) {
        return waitForArticleToAppear(articleInListName)
                .getAttribute("text");
    }
}
