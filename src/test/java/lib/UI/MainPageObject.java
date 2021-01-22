package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    private AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public boolean assertElementHasText(String locator, String expectedText, String errorMessage) {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        String actualText = element.getAttribute("text");
        return actualText.equals(expectedText);
    }


    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementPresentAndSandKeys(String locator, String value, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(locator, errorMessage, timeout);
        resultElement.sendKeys(value);
        return resultElement;
    }

    public WebElement waitForElementPresentAndClick(String locator, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(locator, errorMessage, timeout);
        resultElement.click();
        return resultElement;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void swipeUp(long time) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(time))).moveTo(PointOption.point(x, endY)).release().perform();
    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes) {
        int alreadySwipes = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwipes > maxSwipes) {
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUp(500);
            ++alreadySwipes;
        }

    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresent(locator, "Can't find element", 10).getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public int getAmountOfElement(String locator) {
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public boolean assertElementNotPresent(String locator) {
        int searchResult = getAmountOfElement(locator);
        if (searchResult > 0) {
            throw new AssertionError("An element" + locator + "suppose to be not present");
        } else return true;
    }

    public boolean assertElementPresent(String locator) {
        By by = this.getLocatorByString(locator);
        return driver.findElement(by).isDisplayed();
    }

    public void swipeElementToLeft(String locator, String errorMessage, int timeout) {
        WebElement element = waitForElementPresent(locator+"/..", errorMessage, timeout);
        int rightX = element.getLocation().getX();
        int highY = element.getLocation().getY();
        int leftX = rightX + element.getSize().getWidth() - 5;
        int lowY = highY + element.getSize().getHeight();
        int mediumY = (highY + lowY) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(leftX, mediumY));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)));
        if (Platform.getInstance().isAndroid()){
        action.moveTo(PointOption.point(rightX, mediumY));}
        else {
            int offsetX = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offsetX, 0));
        }
        action.release();
        action.perform();
    }

    public void swipeUpToFindTheElement(String locator, String errorMessage, int maxSwipe) {
        int alreadySwiped = 0;
        while (driver.findElements(this.getLocatorByString(locator)).size() == 0) {
            if (alreadySwiped > maxSwipe) {
                waitForElementPresent(locator, "Can't find element by swiping up\n" + errorMessage, 20);
                return;
            }
            swipeUp(20);
            alreadySwiped++;
        }
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else throw new IllegalArgumentException("Can't get type of locator " + locatorWithType);
    }
    public void clickElementToTheRightUpperCorner(String locator, String error) {
        WebElement element = this.waitForElementPresent(locator+"/..", error, 10);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();
        int pointToClickX= (rightX+width)-3;
        int pointToClickY = middleY;
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
    }
}
