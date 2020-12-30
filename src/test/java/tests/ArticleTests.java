package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitles() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article = articlePageObject.waitAndGetArticleTitle();
        assertEquals("Appium", article);
    }

    @Test
    public void testAssertTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        assertTrue("Article title wasn't found!", articlePageObject.assertTitlePresent());
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }
}
