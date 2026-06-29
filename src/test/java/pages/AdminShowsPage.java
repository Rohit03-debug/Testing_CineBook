package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class AdminShowsPage extends BasePage {
    private final By title = id("manage-shows-title");
    private final By form = id("ms-form");
    private final By dateInput = id("ms-input-date");
    private final By timeInput = id("ms-input-time");
    private final By priceInput = id("ms-input-price");
    private final By seatsInput = id("ms-input-seats");
    private final By table = id("ms-table");
    private final By emptyTable = id("ms-table-empty");
    private final By upcomingFilter = id("ms-filter-upcoming");
    private final By closedFilter = id("ms-filter-closed");
    private final By allFilter = id("ms-filter-all");

    public AdminShowsPage(WebDriver driver) {
        super(driver);
    }

    public AdminShowsPage open() {
        driver.get(ConfigReader.baseUrl() + "/manage-shows");
        visible(title);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(title) && isVisible(form);
    }

    public boolean hasShowFormFields() {
        return isVisible(dateInput) && isVisible(timeInput) && isVisible(priceInput) && isVisible(seatsInput);
    }

    public void switchFilters() {
        click(allFilter);
        click(upcomingFilter);
        click(closedFilter);
    }

    public boolean hasTableOrEmptyState() {
        return isVisible(table) || isVisible(emptyTable);
    }
}
