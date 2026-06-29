package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BookingPage extends BasePage {
    private final By page = id("booking-page");
    private final By confirmButton = id("booking-confirm");
    private final By error = id("booking-error");
    private final By total = id("booking-total");
    private final By seatCount = id("booking-seat-count");
    private final By availableSeats = By.cssSelector("[id^='seat-']:not(.disabled):not([disabled]), .seat-available:not([disabled])");
    private final By shows = By.cssSelector("[id^='show-']:not([disabled])");

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public BookingPage waitForLoaded() {
        visible(page);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(page);
    }

    public boolean selectFirstShowIfPresent() {
        List<WebElement> showButtons = visibleElements(shows).stream()
                .filter(WebElement::isEnabled)
                .toList();
        if (showButtons.isEmpty()) {
            return false;
        }
        click(showButtons.get(0));
        return true;
    }

    public boolean selectFirstAvailableSeat() {
        List<WebElement> seats = visibleElements(availableSeats).stream()
                .filter(WebElement::isEnabled)
                .toList();
        if (seats.isEmpty()) {
            return false;
        }
        click(seats.get(0));
        return true;
    }

    public boolean hasSelectedSeatSummary() {
        return isVisible(seatCount) && !text(seatCount).isBlank() && isVisible(total);
    }

    public void proceedToPay() {
        click(confirmButton);
    }

    public boolean waitForErrorOrStillOnBooking() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(error),
                ExpectedConditions.urlContains("/book")
        ));
        return isVisible(error) || currentUrl().contains("/book");
    }

    public boolean navigatedToPaymentOrSuccess() {
        wait.until(driver -> {
            String url = driver.getCurrentUrl().toLowerCase();
            return url.contains("checkout.stripe") || url.contains("/payment/") || url.contains("stripe.com");
        });
        return true;
    }
}
