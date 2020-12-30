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

    @Test
    public void testSave2Articles() {
        String listName = "Learning programming";
        String firstArticle = "Java (programming language)";
        String secondArticle = "Java version history";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForSkipButtonAndClick();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(firstArticle);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.saveAndAddArticle();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.createListAndAddArticle(listName);
        articlePageObject.closeArticle();
        searchPageObject.clickByArticleWithSubstring(secondArticle);
        articlePageObject.saveAndAddArticle();
        myListsPageObject.addArticleToFolder(listName);
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickViewLists();
        myListsPageObject.swipeArticleToDelete(secondArticle);
        assertTrue(myListsPageObject.waitForArticleToDisAppear(secondArticle));
        String articleInListName = myListsPageObject.getArticleInListTitle(firstArticle);
        myListsPageObject.waitForArticleToAppearAndClick(firstArticle);
        String articleOpenedName = articlePageObject.waitAndGetArticleTitle();
        assertEquals("Title name is different", articleOpenedName, articleInListName);
    }
}
