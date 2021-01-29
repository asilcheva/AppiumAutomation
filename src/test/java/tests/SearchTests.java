package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.SearchPageObject;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testElementHasText() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
    }

    @Test
    public void testElementsHaveTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
            searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        } else {
            searchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
            searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        }
    }

    @Test
    public void testCancelButton() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        if (Platform.getInstance().isAndroid()) {
            Assert.assertTrue(searchPageObject.assertResultIsEmpty());
        } else {
            Assert.assertTrue(searchPageObject.waitForCancelButtonToDisappear());
        }
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
        Assert.assertTrue("There's less than 3 results", results >= 3);
        Assert.assertTrue("There's no such article" + firstArticle, searchPageObject.waitForSearchResult(firstArticle));
        Assert.assertTrue("There's no such article" + secondArticle, searchPageObject.waitForSearchResult(secondArticle));
        Assert.assertTrue("There's no such article" + thirdArticle, searchPageObject.waitForSearchResult(thirdArticle));
    }

    @Test
    public void testSearchCancellation() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        int firstResults = searchPageObject.waitAndGetAmountOfFoundArticles();
        Assert.assertTrue(firstResults > 0);
        searchPageObject.clickCancelButton();
        if (Platform.getInstance().isAndroid()) {
            Assert.assertTrue(searchPageObject.assertResultIsEmpty());
        } else {
            Assert.assertTrue(searchPageObject.waitForCancelButtonToDisappear());
        }
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchText = "asdfgqwe";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForEmptyResult();
    }
}
