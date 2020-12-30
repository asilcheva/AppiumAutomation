package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testElementHasText() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
    }

    @Test
    public void testCancelButton() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        assertTrue(searchPageObject.waitForCancelButtonToDisappear());
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchText = "Linkin Park discography";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.clickByArticleWithSubstring(searchText);
        int results = searchPageObject.waitAndGetAmountOfFoundArticles();
        assertTrue(results > 0);
    }


    @Test
    public void testAmountOfEmptySearch() {
        String searchText = "asdfgqwe";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForEmptyResult();
        assertTrue(searchPageObject.assertNoResultOfSearch());
    }
}
