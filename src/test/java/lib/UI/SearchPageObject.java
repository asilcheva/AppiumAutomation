package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String SEARCH_INIT_ELEMENT;
    protected static String SEARCH_INPUT;
    protected static String SEARCH_RESULT;
    protected static String SEARCH_RESULT_WITH_DESCRIPTION;
    protected static String CANCEL_BUTTON;
    protected static String ALL_ARTICLES;
    protected static String NO_RESULTS;
    protected static String SKIP_BUTTON;
    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT.replace("{substring}", substring);
    }
    private static String getResultSearchElementWithDescription(String title, String description) {
        return SEARCH_RESULT_WITH_DESCRIPTION.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /*TEMPLATES METHODS*/
    public void initSearchInput() {
        this.waitForElementPresentAndClick((SEARCH_INIT_ELEMENT), "Can't find Search input", 5);
        this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Can't find Search input", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementPresentAndSandKeys((SEARCH_INPUT), searchLine, "Can't find search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = SearchPageObject.getResultSearchElement(substring);
        this.waitForElementPresent((searchResultXpath), "Can't find search result", 10);
    }
    public void waitForElementByTitleAndDescription(String title, String description) {
            String searchResultXpath = SearchPageObject.getResultSearchElementWithDescription(title, description);
        this.waitForElementPresent((searchResultXpath), "Can't find search result with such title and description", 16);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent((CANCEL_BUTTON), "Can't find cancel button", 6);
    }

    public boolean waitForCancelButtonToDisappear() {
        return this.waitForElementNotPresent((CANCEL_BUTTON), "Cancel button is still present", 6);
    }

    public void clickCancelButton() {
        this.waitForElementPresentAndClick((CANCEL_BUTTON), "Can't find cancel button", 5);
    }
    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = SearchPageObject.getResultSearchElement(substring);
        this.waitForElementPresentAndClick((searchResultXpath), "Can't find search result", 10);
    }
    public int waitAndGetAmountOfFoundArticles() {
        waitForElementPresent((ALL_ARTICLES), "Can't find articles", 5);
        return this.getAmountOfElement((ALL_ARTICLES));
    }
    public int getAmountOfFoundArticles() {
        return this.getAmountOfElement((ALL_ARTICLES));
    }
    public boolean waitForEmptyResult() {
        return waitForElementPresent((NO_RESULTS), "There is an article", 5).isDisplayed();
    }
    public boolean assertNoResultOfSearch() {
        return this.assertElementPresent((NO_RESULTS));
    }
    public void waitForSkipButtonAndClick(){
        this.waitForElementPresentAndClick((SKIP_BUTTON), "Can't find Skip button", 10);
    }
}
