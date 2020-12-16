import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
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
    public void firstTest() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"), "Can't find search input Java", 15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Can't find search field", 10);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X is still present at the page", 10);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        WebElement article = waitForElementPresentAndClick(By.xpath("org.wikipedia:id/page_list_item_title/*[@text='Java (programming language)']"), "Can't find Search Wikipedia input", 10);
        waitForElementPresent(By.id("org.wikipedia:id/view_page_header_image"), "Can't find the article", 15);
        Assert.assertEquals("There's wrong title", "Java (programming language)", article.getAttribute("text"));
    }

    @Test
    public void testSearchCancellation() {
        waitForElementPresentAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Can't find Skip button", 10);
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_container"), "Can't find Search Wikipedia input", 10);
        waitForElementPresentAndSandKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Can't find search input", 10);
        List<WebElement> elements = waitForListElementsPresent(By.id("org.wikipedia:id/page_list_item_title"), "Can't find any title", 10);
        Assert.assertFalse(elements.isEmpty());
        waitForElementPresentAndClick(By.id("org.wikipedia:id/search_close_btn"), "Can't find X button", 10);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X button is still present", 10);
        boolean presence = waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"), "Some title is still found", 10);
        Assert.assertTrue(presence);
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

    private WebElement waitForElementPresentAndClick(By by, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(by, errorMessage, timeout);
        resultElement.click();
        return resultElement;
    }

    private WebElement waitForElementPresentAndSandKeys(By by, String value, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(by, errorMessage, timeout);
        resultElement.sendKeys(value);
        return resultElement;
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private List<WebElement> waitForListElementsPresent(By by, String errorMessage, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
         return webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
    }

    private WebElement waitForElementAndClear(By by, String errorMassage, long timeout) {
        WebElement element = waitForElementPresent(by, errorMassage, timeout);
        element.clear();
        return element;
    }
}
