package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.MoviesPage;

public class BookingTests extends BaseTest {

    @Test(groups = {"sanity", "regression", "booking", "FRD_2_5"},
            description = "FRD_2.5.7: Proceeding without a selected seat should show validation feedback")
    public void FRD_251_bookingRequiresAtLeastOneSeat() {
        loginAsUser();
        BookingPage bookingPage = openFirstBookableMovie();
        bookingPage.proceedToPay();
        Assert.assertTrue(bookingPage.waitForErrorOrStillOnBooking(),
                "Booking should ask the user to select at least one seat.");
    }

    @Test(groups = {"payment", "destructive", "booking", "TS_103", "TC_107"},
            description = "TC_107: Verify booking is successful for selected movie through payment redirect boundary")
    public void TC_107_bookingCanProceedToPaymentForSelectedMovie() {
        skipIfPaymentTestsDisabled();
        skipIfDestructiveTestsDisabled();
        loginAsUser();
        BookingPage bookingPage = openFirstBookableMovie();
        bookingPage.selectFirstShowIfPresent();
        if (!bookingPage.selectFirstAvailableSeat()) {
            throw new SkipException("No available seats exist for the selected show.");
        }
        Assert.assertTrue(bookingPage.hasSelectedSeatSummary(), "Selected seat and total should appear in booking summary.");
        bookingPage.proceedToPay();
        Assert.assertTrue(bookingPage.navigatedToPaymentOrSuccess(), "Proceeding should redirect to payment or payment result page.");
    }

    private BookingPage openFirstBookableMovie() {
        MoviesPage moviesPage = new MoviesPage(driver).open();
        if (!moviesPage.bookFirstMovie()) {
            throw new SkipException("No bookable movie is available.");
        }
        return new BookingPage(driver).waitForLoaded();
    }
}
