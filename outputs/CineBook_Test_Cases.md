# CineBook Selenium Test Cases

Source files used:
- `Test scenarios cinebook.xlsx`
- `FRD_CineBook_Movie_Ticket_Booking_Platform_Flow_Numbered.docx`
- Live app selector inspection from `https://cinebookfrontend.netlify.app`

## Excel Scenario Coverage

| Scenario | Test Case | Automation Method | Groups | Expected Result |
|---|---|---|---|---|
| TS_101 Browse and filter movies | TC_101 Movies can be searched | `MovieBrowsingTests.TC_101_moviesCanBeSearched` | regression, movie, TS_101, TC_101 | Search value is accepted and matching movie results or empty-state feedback appears. |
| TS_101 Browse and filter movies | TC_102 Movies can be filtered by location | `MovieBrowsingTests.TC_102_moviesCanBeFilteredByLocation` | regression, movie, TS_101, TC_102 | Location selection refreshes the movie list. |
| TS_101 Browse and filter movies | TC_103 Movies can be filtered by theater | `MovieBrowsingTests.TC_103_moviesCanBeFilteredByTheater` | regression, movie, TS_101, TC_103 | Theater selection refreshes the movie list. |
| TS_102 View movie card and trailer | TC_104 Movie card layout | `MovieBrowsingTests.TC_104_movieCardLayoutShowsAllRequiredFields` | known-defect, movie, TS_102, TC_104 | Movie cards display poster, title, genre/language/review information, Book Tickets, and View Trailer. |
| TS_102 View movie card and trailer | TC_105 Available trailer modal | `MovieBrowsingTests.TC_105_availableTrailerModalOpens` | known-defect, movie, trailer, TS_102, TC_105 | Trailer modal/player opens for a movie with a trailer. |
| TS_102 View movie card and trailer | TC_106 Unavailable trailer state | `MovieBrowsingTests.TC_106_unavailableTrailerHasUnavailableMessage` | regression, movie, trailer, TS_102, TC_106 | Unavailable trailers show a clear unavailable message. |
| TS_103 Movie booking | TC_107 Successful selected movie booking | `BookingTests.TC_107_bookingCanProceedToPaymentForSelectedMovie` | payment, destructive, booking, TS_103, TC_107 | Selected seat summary appears and Proceed to Pay reaches payment boundary. |
| TS_104 Browse theatres and showtimes | TC_108 Theatres can be searched | `TheaterBrowsingTests.TC_108_theatersCanBeSearched` | regression, theater, TS_104, TC_108 | Search value is accepted and theater results or empty-state feedback appears. |
| TS_104 Browse theatres and showtimes | TC_109 Open showtimes from theatre | `TheaterBrowsingTests.TC_109_showtimesOpenFromTheaterResult` | regression, theater, TS_104, TC_109 | Theater detail opens and shows showtimes or empty-state feedback. |
| TS_105 Select available theatre show | TC_110 Select available show | `TheaterBrowsingTests.TC_110_userCanSelectAvailableShowFromTheaterPage` | regression, theater, booking, TS_105, TC_110 | Selecting a show opens the booking page. |

## Additional FRD Coverage

| Requirement | Automation Method | Groups | Expected Result |
|---|---|---|---|
| FRD 2.1 Login page renders | `AuthTests.FRD_201_loginPageRenders` | smoke, auth, FRD_2_1 | Login page shows username/password/sign-in controls. |
| FRD 2.1.6 Invalid login handling | `AuthTests.FRD_202_invalidLoginStaysOnLoginPage` | smoke, auth, FRD_2_1 | Invalid login stays on login with validation or error feedback. |
| FRD 2.2 Moviegoer registration screen | `AuthTests.FRD_203_registerPageRendersForMoviegoer` | smoke, auth, FRD_2_2 | Register page supports moviegoer role. |
| FRD 2.2.3 Theater owner registration | `AuthTests.FRD_204_theaterOwnerRegistrationFieldsAppear` | smoke, auth, FRD_2_2 | Theater name and location inputs appear for theater owner role. |
| Route protection | `AuthTests.FRD_205_protectedMoviesRouteRedirectsToLogin` | smoke, auth, security | Unauthenticated `/movies` access redirects to `/login`. |
| FRD 2.1 Valid moviegoer login | `AuthTests.FRD_206_validMoviegoerLoginCreatesSession` | regression, auth, FRD_2_1 | Valid user login displays logged-in navigation. |
| FRD 2.1 Logout | `AuthTests.FRD_207_logoutReturnsToLogin` | regression, auth, FRD_2_1 | Logout returns to login page. |
| FRD 2.5.7 Seat selection validation | `BookingTests.FRD_251_bookingRequiresAtLeastOneSeat` | regression, booking, FRD_2_5 | Booking without seats shows validation and remains on booking page. |
| FRD 2.7.1-2.7.2 My Bookings list | `MyBookingsTests.FRD_271_myBookingsPageDisplaysBookingsOrEmptyState` | regression, my-bookings, FRD_2_7 | My Bookings displays list or empty state. |
| FRD 2.7.3 Booking status tabs | `MyBookingsTests.FRD_273_myBookingsStatusTabsAreSelectable` | regression, my-bookings, FRD_2_7 | All/confirmed/cancelled tabs are selectable. |
| FRD 2.7.4-2.7.5 Cancellation dialog | `MyBookingsTests.FRD_274_cancelDialogOpensWithoutConfirmingCancellation` | regression, my-bookings, FRD_2_7 | Cancel dialog opens without confirming cancellation. |
| FRD 2.9 Admin movie management | `AdminTests.FRD_291_adminManageMoviesPageLoads` | regression, admin, FRD_2_9 | Manage Movies form and table/empty state load. |
| FRD 2.9.4 Admin movie form validation | `AdminTests.FRD_294_adminMovieFormRequiresMandatoryFields` | regression, admin, FRD_2_9 | Empty movie form remains active for validation. |
| FRD 2.10 Admin show management | `AdminTests.FRD_2101_adminManageShowsPageLoads` | regression, admin, FRD_2_10 | Manage Shows form and table/empty state load. |
| FRD 2.11 Admin booking ledger | `AdminTests.FRD_2111_adminBookingsPageLoadsAndTabsWork` | regression, admin, FRD_2_11 | Admin bookings page and status tabs work. |
| FRD 2.11 Analytics dashboard | `AdminTests.FRD_2114_adminAnalyticsDashboardLoads` | regression, admin, FRD_2_11 | Analytics KPI cards load and refresh control is available. |

## Known Defects From Workbook

| Defect | Related Test | Default Suite Behavior |
|---|---|---|
| DF_101 Movie poster missing on movie card | TC_104 | Excluded by `known-defect` group unless explicitly included. |
| DF_102 Trailer modal opens but video is unavailable | TC_105 | Excluded by `known-defect` group unless explicitly included. |

## Default Group Strategy

The checked-in `testng.xml` includes `smoke` and `regression`, and excludes `known-defect`, `payment`, and `destructive`. Credential-dependent tests skip until valid values are set in `config.properties` or passed with Maven `-D` properties.
