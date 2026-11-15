# STAYHUB – Testing Documentation

## 1. Testing Overview
STAYHUB is a Java console-based property booking system. Manual functional testing was performed to verify booking, cancellation, property creation, input validation, and exception handling.

## 2. Test Results
| Test ID | Test Scenario | Expected Result | Status |
|---|---|---|---|
| TC-01 | Empty guest name | Reject blank guest name | PASS |
| TC-02 | Non-numeric price | Display error without crashing | PASS |
| TC-03 | Zero or negative price | Reject invalid price | PASS |
| TC-04 | Invalid floor number | Reject negative or non-numeric input | PASS |
| TC-05 | Invalid guest capacity | Reject zero or invalid input | PASS |
| TC-06 | Invalid bed count | Reject zero or invalid input | PASS |
| TC-07 | Reservation ID generation | Avoid duplicate IDs after cancellation | PASS |
| TC-08 | Blank property title or location | Reject empty details | PASS |
| TC-09 | Invalid boolean input | Accept only true or false | PASS |
| TC-10 | Property ID generation | Assign sequential unique IDs | PASS |
| TC-11 | Invalid property type | Reject unsupported menu choice | PASS |
| TC-12 | Double booking | Reject unavailable property | PASS |
| TC-13 | Reservation cancellation | Restore property availability | PASS |
| TC-14 | Invalid booking duration | Reject negative or non-numeric nights | PASS |
| TC-15 | Invalid property ID | Display appropriate error | PASS |
| TC-16 | Invalid reservation ID | Reject invalid cancellation | PASS |

## 3. Screenshot Evidence
- [Main Menu](../screenshots/main-menu.png)
- [Property Listings](../screenshots/property-listings.png)
- [Successful Booking](../screenshots/successful-booking.png)
- [Double-Booking Prevention](../screenshots/double-booking-error.png)
- [Reservation Cancellation](../screenshots/cancellation.png)
- [Host Property Creation](../screenshots/host-listing.png)
- [Input Validation](../screenshots/input-validation.png)

## 4. Known Limitations
- Data is stored in memory and resets when the program restarts.
- Properties have a simple available/unavailable status rather than date-based availability.
- Reservation and property ID counters reset when the program restarts.
- The application uses a console-based interface.

## 5. Testing Conclusion
The team-reported manual tests passed their expected outcomes. The tested functions demonstrate input validation, exception handling, property management, booking, and cancellation. Further regression testing is recommended after future code changes.
