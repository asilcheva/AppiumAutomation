package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Tests for article")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Change condition")})
    @DisplayName("Rotate article screen")
    @Severity(SeverityLevel.MINOR)
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
        Assert.assertEquals("Text is different", beforeAttribute, afterFirstRotationAttribute);
        rotateScreenPortrait();
        String afterSecondRotationAttribute = articlePageObject.waitAndGetArticleTitle();
        Assert.assertEquals("Text is different", beforeAttribute, afterSecondRotationAttribute);
    }
    @Test
    @DisplayName("Move app to background and launch from it")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Change condition")})
    @Severity(SeverityLevel.MINOR)
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
        Assert.assertTrue(searchPageObject.waitForSearchResult(searchText));
    }
}
