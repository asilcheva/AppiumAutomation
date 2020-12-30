package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private final static String searchInitElement = "org.wikipedia:id/search_container";
    private final static String searchInput = "org.wikipedia:id/search_src_text";
    private final static String searchResult = "//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='{substring}']";
    private final static String CANCELBUTTON = "org.wikipedia:id/search_close_btn";
    private final static String ALL_ARTICLES= "org.wikipedia:id/page_list_item_title";
    private final static String NO_RESULTS=  "//android.widget.TextView[@text='No results']";

    private static String getResultSearchElement(String substring) {
        return searchResult.replace("{substring}", substring);
    }

    public void initSearchInput() {
        this.waitForElementPresentAndClick(By.id(searchInitElement), "Can't find Search input", 5);
        this.waitForElementPresent(By.id(searchInitElement), "Can't find Search input", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementPresentAndSandKeys(By.id(searchInput), searchLine, "Can't find search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = SearchPageObject.getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath), "Can't find search result", 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(CANCELBUTTON), "Can't find cancel button", 6);
    }

    public boolean waitForCancelButtonToDisappear() {
        return this.waitForElementNotPresent(By.id(CANCELBUTTON), "Cancel button is still present", 6);
    }

    public void clickCancelButton() {
        this.waitForElementPresentAndClick(By.id(CANCELBUTTON), "Can't find cancel button", 5);
    }
    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = SearchPageObject.getResultSearchElement(substring);
        this.waitForElementPresentAndClick(By.xpath(searchResultXpath), "Can't find search result", 10);
    }
    public int waitAndGetAmountOfFoundArticles() {
        waitForElementPresent(By.id(ALL_ARTICLES), "Can't find articles", 5);
        return this.getAmountOfElement(By.id(ALL_ARTICLES));
    }
    public int getAmountOfFoundArticles() {
        return this.getAmountOfElement(By.id(ALL_ARTICLES));
    }
    public boolean waitForEmptyResult() {
        return waitForElementPresent(By.xpath(NO_RESULTS), "There is an article", 5).isDisplayed();
    }
    public boolean assertNoResultOfSearch() {
        return this.assertElementPresent(By.xpath(NO_RESULTS));
    }
    public void waitForSkipButtonAndClick(){
        this.waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
    }
}
