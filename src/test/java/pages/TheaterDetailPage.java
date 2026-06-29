package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TheaterDetailPage extends BasePage {
    private final org.openqa.selenium.By page = id("theater-detail-page");
    private final org.openqa.selenium.By showButtons = idStartsWith("theater-show-");
    private final org.openqa.selenium.By emptyState = id("theater-detail-empty");

    public TheaterDetailPage(WebDriver driver) {
        super(driver);
    }

    public TheaterDetailPage waitForLoaded() {
        visible(page);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(page);
    }

    public boolean hasShowsOrEmptyState() {
        return !visibleElements(showButtons).isEmpty() || isVisible(emptyState);
    }

    public boolean selectFirstShow() {
        List<WebElement> shows = visibleElements(showButtons).stream()
                .filter(WebElement::isEnabled)
                .toList();
        if (shows.isEmpty()) {
            return false;
        }
        click(shows.get(0));
        wait.until(ExpectedConditions.urlContains("/book"));
        return true;
    }
}
