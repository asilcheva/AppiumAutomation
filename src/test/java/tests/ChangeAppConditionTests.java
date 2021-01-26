package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        if (Platform.getInstance().isMW()){
            return;
        }
        String searchText = "Java (programming language)";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.clickByArticleWithSubstring(searchText);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
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
        if (Platform.getInstance().isMW()){
            return;
        }
        String searchText = "Appium";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForSearchResult(searchText);
        backgroundApp(10);
        searchPageObject.waitForSearchResult(searchText);
    }
}
