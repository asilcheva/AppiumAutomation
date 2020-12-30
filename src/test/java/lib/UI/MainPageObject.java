package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageObject {
    private AppiumDriver driver;
    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }
    public boolean assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = driver.findElement(by);
        String actualText = element.getAttribute("text");
        return actualText.equals(expectedText);
    }


    public boolean waitForElementNotPresent(By by, String errorMessage, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.withMessage(errorMessage);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitForElementPresentAndSandKeys(By by, String value, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(by, errorMessage, timeout);
        resultElement.sendKeys(value);
        return resultElement;
    }

    public WebElement waitForElementPresentAndClick(By by, String errorMessage, long timeout) {
        WebElement resultElement = waitForElementPresent(by, errorMessage, timeout);
        resultElement.click();
        return resultElement;
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeout) {
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
    public int getAmountOfElement(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }
    public boolean assertElementNotPresent(By by) {
        int searchResult = getAmountOfElement(by);
        if (searchResult > 0) {
            throw new AssertionError("An element" + by.toString() + "suppose to be not present");
        } else return true;
    }
    public boolean assertElementPresent(By by) {
        return driver.findElement(by).isDisplayed();
    }
    public void swipeElementToLeft(By by, String errorMessage, int timeout) {
        WebElement element = waitForElementPresent(by, errorMessage, timeout);
        int rightX = element.getLocation().getX();
        int highY = element.getLocation().getY();
        int leftX = rightX + element.getSize().getWidth()-5;
        int lowY = highY + element.getSize().getHeight();
        int mediumY = (highY + lowY) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(leftX, mediumY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(rightX, mediumY))
                .release()
                .perform();
    }

    public void swipeUpToFindTheElement(By by, String errorMessage, int maxSwipe) {
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
}
