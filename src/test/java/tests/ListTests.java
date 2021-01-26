package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.MyListsPageObjectFactory;
import lib.UI.factories.NavigationUIFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ListTests extends CoreTestCase {
    private String login= "TestAutomationAn";
    private String password= "/'].;[,lp";
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.saveAndAddArticle();
            myListsPageObject.createListAndAddArticle("Learning programming");
            articlePageObject.closeArticle();
            navigationUI.clickSaved();
            myListsPageObject.openFolderByName("Learning programming");
        } else if (Platform.getInstance().isIOS()){
            articlePageObject.addArticleToMySaved();
            articlePageObject.clickClose();
            articlePageObject.closeArticle();
            navigationUI.clickSaved();
        }
        else {
            articlePageObject.addArticleToMySaved();
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.clickAuthButton();
            authorizationPageObject.enterLogin(login, password);
            authorizationPageObject.submitForm();
            articlePageObject.waitForTitleElement();
            articlePageObject.addArticleToMySaved();
            navigationUI.openNavigation();
            navigationUI.clickSaved();

        }
        myListsPageObject.swipeArticleToDelete("Java (programming language)");
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() throws InterruptedException {
        String listName = "Learning programming";
        String firstArticle = "Java (programming language)";
        String secondArticle = "Java version history";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(firstArticle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.saveAndAddArticle();
            myListsPageObject.createListAndAddArticle(listName);
            articlePageObject.closeArticle();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            searchPageObject.clickByArticleWithSubstring(secondArticle);
            articlePageObject.addArticleToMySaved();
            myListsPageObject.addArticleToFolder(listName);
            navigationUI.clickViewLists();
        } else if (Platform.getInstance().isIOS()){
            articlePageObject.addArticleToMySaved();
            articlePageObject.clickClose();
            articlePageObject.closeArticle();
            searchPageObject.initSearchInput();
            searchPageObject.clickByArticleWithSubstring(secondArticle);
            articlePageObject.addArticleToMySaved();
            articlePageObject.closeArticle();
            navigationUI.clickSaved();
        }
        else {
            articlePageObject.addArticleToMySaved();
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.clickAuthButton();
            authorizationPageObject.enterLogin(login, password);
            authorizationPageObject.submitForm();
            articlePageObject.waitForTitleElement();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            searchPageObject.clickByArticleWithSubstring(secondArticle);
            articlePageObject.addArticleToMySaved();
            navigationUI.openNavigation();
            navigationUI.clickSaved();
        }
        myListsPageObject.swipeArticleToDelete(secondArticle);
        assertTrue(myListsPageObject.waitForArticleToAppear(firstArticle).isDisplayed());
    }
}
