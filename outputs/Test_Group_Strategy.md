# CineBook Test Group Strategy

Source workbook:
`C:\Users\2492244\Downloads\CineBookTestingReport (4) (1).xlsx`

## Smoke

Smoke tests verify that the application starts, key public pages render, and route protection works before deeper testing.

Suite file:
`src/test/resources/smoke-testng.xml`

Included group:
`smoke`

Current coverage:
- Login page renders.
- Invalid login stays on login with feedback.
- Register page renders for Moviegoer.
- Theater Owner registration fields appear.
- Protected `/movies` route redirects unauthenticated users to `/login`.

## Sanity

Sanity tests verify that the latest build is usable for core authenticated Moviegoer and Admin workflows.

Suite file:
`src/test/resources/sanity-testng.xml`

Included group:
`sanity`

Current coverage:
- Valid Moviegoer login and logout.
- Movie search.
- Theater search and opening showtimes.
- Booking validation without seat selection.
- My Bookings page list or empty state.
- Admin Manage Movies, Manage Shows, Manage Bookings, and Analytics pages.
- Admin booking ledger theater scoping.
- Analytics dashboard load and refresh.

## Regression

Regression tests cover the broader functional workflow set from the workbook and FRD coverage. Known defects, payment, and destructive flows are excluded by default.

Suite file:
`src/test/resources/regression-testng.xml`

Included group:
`regression`

Excluded groups:
`known-defect`, `payment`, `destructive`

Current coverage:
- Auth/session behavior.
- Movie search/filter/trailer unavailable behavior.
- Theater browsing and show selection.
- Booking validation.
- My Bookings tabs and cancel dialog opening.
- Admin movie/show/booking/analytics workflows.
- Admin Booking Management 11.1 scripts.
- Admin Analytics 12.1 scripts, excluding known defects.

## Known Defects And Screenshots

Defect cases are grouped under `known-defect`. The existing `listeners.TestListener` captures screenshots on test failure when `screenshotOnFailure=true` in `config.properties`.

Suite file:
`src/test/resources/known-defect-screenshot-testng.xml`

Included group:
`known-defect`

Automated defect mappings:
- `DF_101`: Movie poster is not displayed in the movie card.
- `DF_102`: Movie trailer modal opens but trailer does not play.
- `DF_001`: Audience Interest appears not scoped to the current theater or empty state.

Screenshot output:
`screenshots/`

Report attachment:
Screenshots are attached to the Extent report for failed defect tests.
