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
