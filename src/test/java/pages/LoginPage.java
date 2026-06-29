package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

public class LoginPage extends BasePage {
    private final By page = id("login-page");
    private final By usernameInput = id("login-username-input");
    private final By passwordInput = id("login-password-input");
    private final By submitButton = id("login-submit-btn");
    private final By error = id("login-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(ConfigReader.baseUrl() + "/login");
        visible(usernameInput);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(page) || isVisible(usernameInput);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);
    }

    public boolean waitForErrorOrLoginPage() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(error),
                ExpectedConditions.urlContains("/login")
        ));
        return isVisible(error) || currentUrl().contains("/login");
    }

    public void waitUntilLoggedIn() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/movies"),
                ExpectedConditions.urlContains("/manage-movies"),
                ExpectedConditions.visibilityOfElementLocated(By.id("navbar-logout-btn"))
        ));
    }
}
