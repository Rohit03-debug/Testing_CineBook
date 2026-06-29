package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

import java.util.List;

public class AdminBookingsPage extends BasePage {
    private final By page = id("ab-page");
    private final By table = id("ab-table");
    private final By emptyRow = id("ab-empty-row");
    private final By filterTrigger = id("ab-filter-trigger");
    private final By filterRows = idStartsWith("ab-filter-row-");
    private final By bookingRows = idStartsWith("ab-row-");
    private final By adminTheaterName = id("navbar-admin-theater-name");
    private final By allTab = id("ab-tab-all");
    private final By confirmedTab = id("ab-tab-confirmed");
    private final By cancelledTab = id("ab-tab-cancelled");

    public AdminBookingsPage(WebDriver driver) {
        super(driver);
    }

    public AdminBookingsPage open() {
        driver.get(ConfigReader.baseUrl() + "/manage-bookings");
        visible(page);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(page);
    }

    public void switchStatusTabs() {
        click(allTab);
        click(confirmedTab);
        click(cancelledTab);
    }

    public void selectCancelledTab() {
        click(cancelledTab);
    }

    public boolean applyMovieCancelledFilterWithNoMatches() {
        click(filterTrigger);
        int movieCount = elements(filterRows).size();

        for (int i = 0; i < movieCount; i++) {
            if (i > 0) {
                click(filterTrigger);
            }
            List<WebElement> movies = elements(filterRows);
            if (i >= movies.size()) {
                return false;
            }
            click(movies.get(i));
            selectCancelledTab();
            if (isEmptyRowDisplayed()) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmptyRowDisplayed() {
        return isVisible(emptyRow);
    }

    public String emptyRowMessage() {
        return text(emptyRow);
    }

    public int ledgerRowCount() {
        return visibleElements(bookingRows).size();
    }

    public boolean hasTableOrEmptyRow() {
        return isVisible(table) || isVisible(emptyRow);
    }

    public String loggedInTheaterName() {
        return text(adminTheaterName);
    }

    public boolean allLedgerRowsBelongToLoggedInTheater() {
        String theater = loggedInTheaterName();
        return visibleElements(bookingRows).stream()
                .map(row -> row.findElements(By.tagName("td")))
                .filter(cells -> cells.size() >= 3)
                .map(cells -> cells.get(2).getText())
                .allMatch(movieShowCell -> movieShowCell.contains(theater));
    }
}
