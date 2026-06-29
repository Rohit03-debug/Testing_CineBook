package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

import java.util.List;

public class AnalyticsPage extends BasePage {
    private final By page = id("an-page");
    private final By revenueKpi = id("an-kpi-revenue");
    private final By seatsKpi = id("an-kpi-seats");
    private final By upcomingKpi = id("an-kpi-upcoming");
    private final By cancelKpi = id("an-kpi-cancel");
    private final By revenueValue = id("analytics-revenue-value");
    private final By seatsValue = id("analytics-seats-value");
    private final By upcomingValue = id("analytics-upcoming-value");
    private final By cancelValue = id("analytics-cancel-value");
    private final By refreshButton = id("an-refresh");
    private final By movieFilterTrigger = id("an-filter-trigger");
    private final By movieFilterRows = idStartsWith("analytics-filter-row-");
    private final By ticketBookingsTitle = id("analytics-bookings-title");
    private final By occupancyTitle = id("analytics-occupancy-title");
    private final By topPerformanceTitle = id("analytics-top-performance-title");
    private final By topRatedTitle = id("analytics-top-rated-title");
    private final By audienceInterestTitle = id("analytics-interest-title");
    private final By topRatedEmpty = id("analytics-top-rated-empty");
    private final By audienceInterestEmpty = id("analytics-interest-empty");

    public AnalyticsPage(WebDriver driver) {
        super(driver);
    }

    public AnalyticsPage open() {
        driver.get(ConfigReader.baseUrl() + "/analytics");
        visible(page);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(page);
    }

    public boolean hasKpis() {
        return isVisible(revenueKpi) && isVisible(seatsKpi) && isVisible(upcomingKpi) && isVisible(cancelKpi);
    }

    public boolean hasDashboardSections() {
        return isVisible(ticketBookingsTitle)
                && isVisible(occupancyTitle)
                && isVisible(topPerformanceTitle)
                && isVisible(topRatedTitle)
                && isVisible(audienceInterestTitle);
    }

    public String metricSnapshot() {
        return String.join("|",
                text(revenueValue),
                text(seatsValue),
                text(upcomingValue),
                text(cancelValue));
    }

    public void refresh() {
        if (isVisible(refreshButton)) {
            click(refreshButton);
        }
    }

    public boolean movieFilterIsAvailable() {
        return isVisible(movieFilterTrigger);
    }

    public boolean selectFirstMovieFilterIfAvailable() {
        click(movieFilterTrigger);
        List<WebElement> movies = visibleElements(movieFilterRows);
        if (movies.isEmpty()) {
            click(movieFilterTrigger);
            return false;
        }
        click(movies.get(0));
        return true;
    }

    public void selectGrouping(String grouping) {
        click(id("an-group-" + grouping));
    }

    public String ticketBookingsHeaderText() {
        return text(ticketBookingsTitle);
    }

    public boolean hasNoRatingsEmptyState() {
        return isVisible(topRatedEmpty) && text(topRatedEmpty).contains("No ratings yet");
    }

    public boolean hasNoAudienceInterestEmptyState() {
        return isVisible(audienceInterestEmpty)
                && text(audienceInterestEmpty).contains("No audience interest recorded yet");
    }
}
