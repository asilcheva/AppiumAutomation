package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testElementHasText() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
    }
    @Test
    public void testElementsHaveTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
        searchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }

    @Test
    public void testCancelButton() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        assertTrue(searchPageObject.waitForCancelButtonToDisappear());
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchText = "Linkin Park discography";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.clickByArticleWithSubstring(searchText);
        int results = searchPageObject.waitAndGetAmountOfFoundArticles();
        assertTrue(results > 0);
    }
    @Test
    public void testSearchCancellation() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        int firstResults = searchPageObject.waitAndGetAmountOfFoundArticles();
        assertTrue(firstResults > 0);
        searchPageObject.clickCancelButton();
        int lastResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue(lastResults==0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchText = "asdfgqwe";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForEmptyResult();
        assertTrue(searchPageObject.assertNoResultOfSearch());
    }
}
