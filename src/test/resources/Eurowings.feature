@all
Feature: Verify flight status

  Background:
    Given I am using "Chrome" browser

  Scenario Outline: Verify Flight route
    Given I am on Eurowings page
    When I click on Flight route radio button
    Then I see correct Flight route inputs
    When I pick "<Departure_airport>" as departure airport
    And I pick "<Destination_airport>" as destination airport
    And I pick "<Departure_date>" as departure date
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
    When I input "<Wrong_flight_number>" flight number
    And I pick "<Departure_date>" as departure date
    And I confirm to show flight status
    Then I see no flights
    Examples:
      | Wrong_flight_number | Departure_date |
      | EW-0000             | Today          |
      | XZ-9999             | Tomorrow       |
