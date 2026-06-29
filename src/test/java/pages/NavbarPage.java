package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

import java.util.List;

public class NavbarPage extends BasePage {
    private final By moviesTab = id("nav-tab-movies");
    private final By theatersTab = id("nav-tab-theaters");
    private final By bookingsTab = id("nav-tab-bookings");
    private final By logoutButton = id("navbar-logout-btn");
    private final By locationTrigger = id("nav-location-trigger");
    private final By locationMenu = id("nav-location-menu");

    public NavbarPage(WebDriver driver) {
        super(driver);
    }

    public void goToMovies() {
        click(moviesTab);
        wait.until(ExpectedConditions.urlContains("/movies"));
    }

    public void goToTheaters() {
        click(theatersTab);
        wait.until(ExpectedConditions.urlContains("/theaters"));
    }

    public void goToMyBookings() {
        click(bookingsTab);
        wait.until(ExpectedConditions.urlContains("/my-bookings"));
    }

    public void logout() {
        click(logoutButton);
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    public boolean isLogoutVisible() {
        return isVisible(logoutButton);
    }

    public boolean selectFirstLocation() {
        if (!isVisible(locationTrigger)) {
            return false;
        }
        click(locationTrigger);
        visible(locationMenu);
        List<WebElement> locations = driver.findElements(By.cssSelector("[id^='nav-location-row-']:not(#nav-location-row-all)"));
        if (locations.isEmpty()) {
            return false;
        }
        click(locations.get(0));
        return true;
    }

    public String selectedLocationText() {
        return isVisible(locationTrigger) ? text(locationTrigger) : "";
    }
}
