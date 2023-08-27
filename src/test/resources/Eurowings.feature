@all
Feature: Verify flight status

  Background:
    Given I am using "Chrome" browser

  Scenario Outline: Verify Flight route
    Given I am on Eurowings page
    When I click on Flight route radio button
    Then I see correct Flight route inputs
    When I pick departure airport as "<Departure_airport>"
    And I pick destination airport as "<Destination_airport>"
    And I pick departure date as "<Departure_date>"
    And I confirm to show flight status
    Then I see list of my searched flight
    Examples:
      | Departure_airport | Destination_airport | Departure_date |
      | CGN               | BER                 | Today          |
      | CGN               | BER                 | Tomorrow       |

  Scenario Outline: Verify wrong Flight number
    Given I am on Eurowings page
    When I click on Flight number radio button
    Then I see correct Flight number inputs
    When I input flight number as "<Wrong_flight_number>"
    And I pick departure date as "<Departure_date>"
    And I confirm to show flight status
    Then I see no flights
    Examples:
      | Wrong_flight_number | Departure_date |
      | EW-0000             | Today          |
      | XZ-9999             | Tomorrow       |
