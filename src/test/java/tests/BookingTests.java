package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BookingPage;

public class BookingTests extends BaseTest {

    @DataProvider(name = "movies")
    public Object[][] movieIds() {
        return new Object[][] { { "movie-book-3", "show-12" } };
    }

    @Test(dataProvider = "movies", groups = { "regression", "booking",
            "FRD_2_5" }, description = "FRD_2.5.7: Proceeding without a selected seat should show validation feedback")
    public void FRD_251_bookingRequiresAtLeastOneSeat(String movieId, String showId) throws InterruptedException {
        loginAsUser();
        BookingPage bookingPage = new BookingPage(driver).waitForLoaded();
        bookingPage.selectMovie(movieId);
        bookingPage.selectShow(showId);
        bookingPage.proceedToPay();
        Assert.assertTrue(bookingPage.waitForErrorOrStillOnBooking(),
                "Booking should ask the user to select at least one seat.");
    }

    @Test(dataProvider = "movies", groups = { "payment", "destructive", "booking", "TS_103",
            "TC_107" }, description = "TC_107: Verify booking is successful for selected movie through payment redirect boundary")
    public void TC_107_bookingCanProceedToPaymentForSelectedMovie(String movieId, String showId)
            throws InterruptedException {
        // skipIfPaymentTestsDisabled();
        // skipIfDestructiveTestsDisabled();
        loginAsUser();
        BookingPage bookingPage = new BookingPage(driver).waitForLoaded();
        bookingPage.selectMovie(movieId);
        bookingPage.selectShow(showId);
        bookingPage.selectFirstAvailableSeat();
        Assert.assertTrue(bookingPage.hasSelectedSeatSummary(),
                "Selected seat and total should appear in booking summary.");
        bookingPage.proceedToPay();
        Assert.assertTrue(bookingPage.navigatedToPaymentOrSuccess(),
                "Proceeding should redirect to payment or payment result page.");
    }

    // @Test(dataProvider = "movies")
    // public void checkLeftSeats(String movieId, String showId) throws
    // InterruptedException {
    // loginAsUser();
    // Thread.sleep(3000);
    // BookingPage bookingPage = new BookingPage(driver);
    // bookingPage.selectMovie(movieId);
    // Thread.sleep(3000);
    // int leftSeats =
    // Integer.parseInt(bookingPage.getSeatsLeft(showId).trim().split(" ")[0]);
    // bookingPage.selectShow(showId);
    // int enabledSeats = bookingPage.findEnabledSeats();
    //
    // Assert.assertEquals(enabledSeats, leftSeats,
    // "The number of enabled seats should match the showtime's available seat
    // count.");
    // }
}
