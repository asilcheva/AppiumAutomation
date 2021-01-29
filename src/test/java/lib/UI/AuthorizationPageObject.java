package lib.UI;

import io.qameta.allure.Step;
import lib.UI.android.AndroidArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private String LOGIN_BUTTON = "xpath://div/a[text() = 'Log in']";
    private String LOGIN_INPUT = "css:input[name='wpName']";
    private String PASSWORD_INPUT = "css:input[name = 'wpPassword']";
    private String SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    @Step("Click Login button")
    public void clickAuthButton() {
        this.waitForElementPresentAndClickable(LOGIN_BUTTON, "Can't find click auth button", 10);
        this.waitForElementPresentAndClick(LOGIN_BUTTON, "Can't find click auth button", 5);
    }

    @Step("Enter login and password")
    public void enterLogin(String login, String password) throws InterruptedException {
        Thread.sleep(500);
        this.waitForElementPresentAndClickable(LOGIN_INPUT, "Can't type login", 5);
        this.waitForElementPresentAndSandKeys(LOGIN_INPUT, login, "Can't type login", 5);
        this.waitForElementPresentAndClickable(PASSWORD_INPUT, "Can't type login", 5);
        this.waitForElementPresentClearAndSandKeys(PASSWORD_INPUT, password, "Can't type password", 5);
    }

    @Step("Click Log in button")
    public void submitForm() {
        this.waitForElementPresentAndClick(SUBMIT_BUTTON, "Can't find submit button", 10);
        this.waitForElementNotPresent(SUBMIT_BUTTON, "There is submit button", 15);
    }
}
