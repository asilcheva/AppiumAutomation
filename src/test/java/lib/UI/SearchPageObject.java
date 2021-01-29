package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObject extends MainPageObject {
    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected String SEARCH_INIT_ELEMENT;
    protected String SEARCH_INPUT;
    protected String SEARCH_RESULT;
    protected String SEARCH_RESULT_WITH_DESCRIPTION;
    protected String CANCEL_BUTTON;
    protected String ALL_ARTICLES;
    protected String NO_RESULTS;
    protected String EMPTY_RESULTS;
    protected String SKIP_BUTTON;

    /*TEMPLATES METHODS*/
    private String getResultSearchElement(String substring) {
        return SEARCH_RESULT.replace("{substring}", substring);
    }

    private String getResultSearchElementWithDescription(String title, String description) {
        return SEARCH_RESULT_WITH_DESCRIPTION.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    /*TEMPLATES METHODS*/
    @Step("Initialization the search field")
    public void initSearchInput() {
        this.waitForElementPresentAndClick((SEARCH_INIT_ELEMENT), "Can't find Search input", 10);
        //this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Can't find Search input", 5);
    }

    @Step("Typing text to the search line '{searchLine}'")
    public void typeSearchLine(String searchLine) {
        this.waitForElementPresentAndSandKeys((SEARCH_INPUT), searchLine, "Can't find search input", 15);
    }

    @Step("Waiting for search result")
    public boolean waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        WebElement element = this.waitForElementPresent((searchResultXpath), "Can't find search result", 15L);
        return element.isDisplayed();
    }

    @Step("Waiting for title and description")
    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchResultXpath = getResultSearchElementWithDescription(title, description);
        this.waitForElementPresent((searchResultXpath), "Can't find search result with such title and description", 16);
    }

    @Step("Waiting for cancel button to appear")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent((CANCEL_BUTTON), "Can't find cancel button", 6);
    }

    @Step("Wating foe cancel button to disappear")
    public boolean waitForCancelButtonToDisappear() {
        return this.waitForElementNotPresent((CANCEL_BUTTON), "Cancel button is still present", 6);
    }

    @Step("Clicking cancel button")
    public void clickCancelButton() {
        this.waitForElementPresentAndClick((CANCEL_BUTTON), "Can't find cancel button", 5);
    }

    @Step("Waiting for search result and select article by substring")
    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresentAndClick((searchResultXpath), "Can't find search result", 10);
    }

    @Step("Get amount of found articles")
    public int waitAndGetAmountOfFoundArticles() {
        waitForElementPresent((ALL_ARTICLES), "Can't find articles", 5);
        return this.getAmountOfElement((ALL_ARTICLES));
    }

    public int getAmountOfFoundArticles() {
        return this.getAmountOfElement((ALL_ARTICLES));
    }

    @Step("Waiting for empty results")
    public boolean waitForEmptyResult() {
        return waitForElementPresent((NO_RESULTS), "There is an article", 20).isEnabled();
    }

    public boolean assertNoResultOfSearch() {
        return this.assertElementPresent((NO_RESULTS));
    }

    public boolean assertResultIsEmpty() {
        return this.assertElementPresent((EMPTY_RESULTS));
    }
}
