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
        searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
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
    public void testAmountOfSearch() {
        String searchText = "Appium";
        String firstArticle = "Appium";
        String secondArticle = "Appius Claudius Caecus";
        String thirdArticle = "AppImage";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        int results = searchPageObject.waitAndGetAmountOfFoundArticles();
        assertTrue("There's less than 3 results", results >=3);
        assertTrue("There's no such article"+firstArticle, searchPageObject.waitForSearchResult(firstArticle));
        assertTrue("There's no such article"+secondArticle, searchPageObject.waitForSearchResult(secondArticle));
        assertTrue("There's no such article"+thirdArticle, searchPageObject.waitForSearchResult(thirdArticle));
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
