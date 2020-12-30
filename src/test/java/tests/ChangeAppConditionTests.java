package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        String searchText = "Java (programming language)";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.clickByArticleWithSubstring(searchText);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String beforeAttribute = articlePageObject.waitAndGetArticleTitle();
        rotateScreenLandscape();
        String afterFirstRotationAttribute = articlePageObject.waitAndGetArticleTitle();
        assertEquals("Text is different", beforeAttribute, afterFirstRotationAttribute);
        rotateScreenPortrait();
        String afterSecondRotationAttribute = articlePageObject.waitAndGetArticleTitle();
        assertEquals("Text is different", beforeAttribute, afterSecondRotationAttribute);
    }
    @Test
    public void testRunFromBackground() {
        String searchText = "Appium";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForSearchResult(searchText);
        backgroundApp(50);
        searchPageObject.waitForSearchResult(searchText);
    }
}
