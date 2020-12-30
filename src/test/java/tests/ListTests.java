package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.MyListsPageObject;
import lib.UI.NavigationUI;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class ListTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.saveAndAddArticle();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.createListAndAddArticle("Learning programming");
        articlePageObject.closeArticle();
        articlePageObject.closeSearchList();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        myListsPageObject.openFolderByName("Learning programming");
        myListsPageObject.swipeArticleToDelete("Appium");
        assertTrue(myListsPageObject.waitForArticleToDisAppear("Appium"));
    }

}
