import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.CoreTestCase;
import lib.UI.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FirstTest extends CoreTestCase {
    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);

    }
    @Test
    public void testSave2Articles() {
        String listName = "Learning programming";
        String firstArticle = "Java (programming language)";
        String secondArticle = "Java version history";
        By saveButton = By.id("org.wikipedia:id/article_menu_bookmark");
        By addToListButton = By.xpath("//android.widget.Button[@text='ADD TO LIST']");
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        mainPageObject.waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='" + firstArticle + "']"), "Can't find search input Java", 15);
        mainPageObject.waitForElementPresentAndClick(saveButton, "Can't find article menu button", 10);
        mainPageObject.waitForElementPresentAndClick(addToListButton, "Can't find Add to list", 5);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='Create new']"), "Can't find Create new link", 10);
        mainPageObject.waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/text_input"), listName, "Can't find text input", 5);
        mainPageObject.waitForElementPresentAndClick(By.id("android:id/button1"), "Can't find OK", 5);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Can't find Back button", 5);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='" + secondArticle + "']"), "Can't find Java version", 15);
        mainPageObject.waitForElementPresentAndClick(saveButton, "Can't find article menu button", 10);
        mainPageObject.waitForElementPresentAndClick(addToListButton, "Can't find Add to list", 5);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='" + listName + "']"), "Can't find the List", 5);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.Button[@text='VIEW LIST']"), "Can't find Back button", 5);
        // waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='" + listName + "']"), "Can't find necessary list", 5);
        mainPageObject.swipeElementToLeft(By.xpath("//android.widget.TextView[@text='" + firstArticle + "']"), "Can't find an article", 15);
        mainPageObject.waitForElementNotPresent(By.xpath("//android.widget.TextView[@text='" + firstArticle + "']"), "Element wasn't deleted", 10);
        WebElement articleInList = mainPageObject.waitForElementPresent(By.xpath("//android.widget.TextView[@text='" + secondArticle + "']"), "Element wasn't deleted", 10);
        String articleInListName = articleInList.getAttribute("text");
        articleInList.click();
        String articleOpenedName = mainPageObject.waitForElementPresent(By.xpath("//android.view.View[1]/android.view.View[1]/android.view.View[1]"), "Can't find an article", 5).getAttribute("text");
        assertEquals("Title name is different", articleOpenedName, articleInListName);
    }
    @Test
    public void testSearchCancellation() {
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        mainPageObject.waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        WebElement element = mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/page_list_item_title"), "Can't find any title", 10);
        Assert.assertTrue(element.isDisplayed());
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/search_close_btn"), "Can't find X button", 10);
        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X button is still present", 10);
        boolean presence = mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"), "Some title is still found", 10);
        Assert.assertTrue(presence);
    }
    @Test
    public void testAssertTitle() {
        String title = "Java (programming language)";
        By element = By.xpath("//android.view.View[@text='" + title + "']");
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        mainPageObject.waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        mainPageObject.waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        mainPageObject.waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='" + title + "']"), "Can't find search input Java", 15);
        Assert.assertTrue("Can't find the title.", mainPageObject.assertElementPresent(element));
    }
}
