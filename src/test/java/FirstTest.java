import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class FirstTest {
    private AppiumDriver driver;
    private static DesiredCapabilities desiredCapabilities;

    @Before
    public void setUp() throws Exception {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "TestDevice");
        desiredCapabilities.setCapability("platformVersion", "11.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "C:\\Anutka\\ProjectsAutotest\\javaAppiumAutomation\\wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testElementHasText() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresent(By.id("org.wikipedia:id/search_src_text"), "Can't find search input", 10);
        Assert.assertTrue(assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Search Wikipedia", "There's no expected text"));
    }

    private boolean assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = driver.findElement(by);
        String actualText = element.getAttribute("text");
        return actualText.equals(expectedText);
    }


    private boolean waitForElementNotPresent(By by, String errorMessage, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    @Test
    public void testSearchWordInResults() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        waitForElementPresent(By.id("org.wikipedia:id/page_list_item_title"), "", 10);
        List<WebElement> list = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (WebElement element : list) {
            Assert.assertTrue(element.getAttribute("text").contains("Java"));
        }
    }

    private WebElement waitForElementPresentAndSandKeys(By by, String value, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(by, errorMessage, timeout);
        resultElement.sendKeys(value);
        return resultElement;
    }

    private WebElement waitForElementPresentAndClick(By by, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(by, errorMessage, timeout);
        resultElement.click();
        return resultElement;
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Test
    public void firstTest() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Can't find search input", 10);
        waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='Appium']"), "Can't find search input Appium", 15);
        waitForElementPresent(By.id("org.wikipedia:id/page_web_view"), "", 15);
        swipeUpToFindTheElement(By.xpath("//*[@text='View article in browser']"), "Can't find the end of the article", 10);
        //Assert.assertTrue(true);
    }

    private void swipeUp(long time) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(time))).moveTo(PointOption.point(x, endY)).release().perform();
    }

    private void swipeUpToFindTheElement(By by, String errorMessage, int maxSwipe) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipe) {
                waitForElementPresent(by, "Can't find element by swiping up\n" + errorMessage, 20);
                return;
            }
            swipeUp(20);
            alreadySwiped++;
        }
    }

    @Test
    public void saveFirstArticleToMyList() {
        String value = "Learning programming";
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"), "Can't find search input Java", 15);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/article_menu_bookmark"), "Can't find article menu button", 10);
        waitForElementPresentAndClick(By.xpath("//android.widget.Button[@text='ADD TO LIST']"), "", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='Create new']"), "Can't find Create new link", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/text_input"), value, "Can't find text input", 5);
        waitForElementPresentAndClick(By.id("android:id/button1"), "Can't find OK", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Can't find Back button", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.ImageButton"), "Can't find Back button", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Can't find My list", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='" + value + "']"), "Can't find necessary list", 5);
        swipeElementToLeft(By.xpath("//android.widget.TextView[@text='Java (programming language)']"), "Can't find an article", 5);
        Assert.assertTrue(waitForElementNotPresent(By.xpath("//android.widget.TextView[@text='Java (programming language)']"), "Element wasn't deleted", 10));
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchText = "Linkin Park Diskography";
        By element = By.id("org.wikipedia:id/page_list_item_title");
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), searchText, "Can't find search input", 10);
        waitForElementPresent(element, "Can't find results", 5);
        int results = getAmountOfElement(element);
        Assert.assertTrue(results > 0);
    }

    private int getAmountOfElement(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchText = "asdfgqwe";
        By element = By.id("org.wikipedia:id/page_list_item_title");
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), searchText, "Can't find search input", 10);
        waitForElementPresent(By.xpath("//android.widget.TextView[@text='No results']"), "Some result was found", 5);
        Assert.assertTrue(assertElementNotPresent(element));
    }

    private boolean assertElementNotPresent(By by) {
        int searchResult = getAmountOfElement(by);
        if (searchResult > 0) {
            throw new AssertionError("An element" + by.toString() + "suppose to be not present");
        } else return true;
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        String searchText = "Java";
        By element = By.xpath("//android.view.View[@text='Java (programming language)']");
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), searchText, "Can't find search input", 10);
        waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"), "Can't find search input Java", 15);
        String beforeAttribute = waitForElementAndGetAttribute(element, "text", "Can't find an attribute", 5);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String afterFirstRotationAttribute = waitForElementAndGetAttribute(element, "text", "Can't find an attribute", 5);
        Assert.assertEquals("Text is different", beforeAttribute, afterFirstRotationAttribute);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String afterSecondRotationAttribute = waitForElementAndGetAttribute(element, "text", "Can't find an attribute", 5);
        Assert.assertEquals("Text is different", beforeAttribute, afterSecondRotationAttribute);
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeout) {
        WebElement element = waitForElementPresent(by, errorMessage, timeout);
        return element.getAttribute(attribute);
    }

    @Test
    public void testRunFromBackground() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        WebElement element = waitForElementPresent(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"), "Can't find search input Java", 15);
        driver.runAppInBackground(Duration.ofMillis(50));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testElementPresent() {
        String title = "Java (programming language)";
        By element = By.xpath("//android.view.View[@text='" + title + "']");
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='" + title + "']"), "Can't find search input Java", 15);
        Assert.assertTrue("Can't find the title.", assertElementPresent(element));
    }

    private boolean assertElementPresent(By by) {
        return driver.findElement(by).isDisplayed();
    }

    @Test
    public void testSave2Articles() {
        String listName = "Learning programming";
        String firstArticle = "Java (programming language)";
        String secondArticle = "Java version history";
        By saveButton = By.id("org.wikipedia:id/article_menu_bookmark");
        By addToListButton = By.xpath("//android.widget.Button[@text='ADD TO LIST']");
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='" + firstArticle + "']"), "Can't find search input Java", 15);
        waitForElementPresentAndClick(saveButton, "Can't find article menu button", 10);
        waitForElementPresentAndClick(addToListButton, "Can't find Add to list", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='Create new']"), "Can't find Create new link", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/text_input"), listName, "Can't find text input", 5);
        waitForElementPresentAndClick(By.id("android:id/button1"), "Can't find OK", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Can't find Back button", 5);
        waitForElementPresentAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='" + secondArticle + "']"), "Can't find Java version", 15);
        waitForElementPresentAndClick(saveButton, "Can't find article menu button", 10);
        waitForElementPresentAndClick(addToListButton, "Can't find Add to list", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='" + listName + "']"), "Can't find the List", 5);
        waitForElementPresentAndClick(By.xpath("//android.widget.Button[@text='VIEW LIST']"), "Can't find Back button", 5);
       // waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@text='" + listName + "']"), "Can't find necessary list", 5);
        swipeElementToLeft(By.xpath("//android.widget.TextView[@text='" + firstArticle + "']"), "Can't find an article", 15);
        waitForElementNotPresent(By.xpath("//android.widget.TextView[@text='" + firstArticle + "']"), "Element wasn't deleted", 10);
        WebElement articleInList = waitForElementPresent(By.xpath("//android.widget.TextView[@text='" + secondArticle + "']"), "Element wasn't deleted", 10);
        String articleInListName = articleInList.getAttribute("text");
        articleInList.click();
        String articleOpenedName = waitForElementPresent(By.xpath("//android.view.View[1]/android.view.View[1]/android.view.View[1]"), "Can't find an article", 5).getAttribute("text");
        Assert.assertEquals("Title name is different", articleOpenedName, articleInListName);
    }
    private void swipeElementToLeft(By by, String errorMessage, int timeout) {
        WebElement element = waitForElementPresent(by, errorMessage, timeout);
        int rightX = element.getLocation().getX();
        int highY = element.getLocation().getY();
        int leftX = rightX + element.getSize().getWidth();
        int lowY = highY + element.getSize().getHeight();
        int mediumY = (highY + lowY) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(leftX, mediumY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(400)))
                .moveTo(PointOption.point(rightX, mediumY))
                .release()
                .perform();
    }
}
