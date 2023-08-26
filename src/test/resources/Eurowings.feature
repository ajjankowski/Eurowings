@all
Feature: Verify flight status

  Background:
    Given I am using "Chrome" browser

#  a) Verify that flight routes from CGN to BER with different travel dates (e.g. today, tomorrow) are shown with the selected dates.
#  b) Add any other tests, which you would like to automate

  Scenario: Verify Flight route
    Given I am on Eurowings page
    When I click on Flight route radio button
    Then I see correct Flight route inputs
    When I pick "CGN" as departure airport
    And I pick "BER" as destination airport
    And I pick %date% as departure date
    And I confirm to show flight status
    Then I see status for searched flight

  Scenario: Verify wrong Flight number
    Given I am on Eurowings page
    When I click on Flight number radio button
    Then I see correct Flight number inputs
    When I input XXX flight number
    And I pick %date% as departure date
    And I confirm to show flight status
    Then I see no flights
