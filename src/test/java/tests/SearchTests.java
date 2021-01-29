package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.SearchPageObject;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Tests for article")
public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Search for article title")
    @Severity(SeverityLevel.BLOCKER)
    public void testElementHasText() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
    }

    @Test
    @DisplayName("Search for article title and description")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Severity(SeverityLevel.CRITICAL)
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
    @DisplayName("Cancel button click")
    @Feature(value = "Search")
    @Severity(SeverityLevel.MINOR)
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
    @DisplayName("Search result assertion")
    @Feature(value = "Search")
    @Severity(SeverityLevel.CRITICAL)
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
    @DisplayName("Search result and Cancel button assertion")
    @Feature(value = "Search")
    @Severity(SeverityLevel.MINOR)
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
    @DisplayName("Incorrect search input assertion")
    @Feature(value = "Search")
    @Severity(SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {
        String searchText = "asdfgqwe";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForEmptyResult();
    }
}
