package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class RegisterPage extends BasePage {
    private final By usernameInput = id("register-username-input");
    private final By passwordInput = id("register-password-input");
    private final By userRoleButton = id("register-role-user-btn");
    private final By adminRoleButton = id("register-role-admin-btn");
    private final By theaterNameInput = id("register-theater-name-input");
    private final By theaterLocationInput = id("register-theater-location-input");
    private final By submitButton = id("register-submit-btn");
    private final By error = id("register-error");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage open() {
        driver.get(ConfigReader.baseUrl() + "/register");
        visible(usernameInput);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(usernameInput);
    }

    public RegisterPage chooseMoviegoer() {
        click(userRoleButton);
        return this;
    }

    public RegisterPage chooseTheaterOwner() {
        click(adminRoleButton);
        visible(theaterNameInput);
        visible(theaterLocationInput);
        return this;
    }

    public boolean theaterOwnerFieldsVisible() {
        return isVisible(theaterNameInput) && isVisible(theaterLocationInput);
    }

    public void submitEmptyForm() {
        click(submitButton);
    }

    public boolean hasValidationMessage() {
        return isVisible(error) || driver.findElements(By.cssSelector("input:invalid")).size() > 0;
    }
}
