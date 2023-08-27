package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EurowingsPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.DriverFactory.driver;
import static utils.TestLogger.log;

public class EurowingsPageStepDefinition {
    EurowingsPage eurowingsPage = new EurowingsPage(driver);

    @Given("I am on Eurowings page")
    public void iAmOnEurowingsPage() {
        log.info("Given I am on Eurowings page");
        String eurowingsUrl = "https://www.eurowings.com/en/information/at-the-airport/flight-status.html";
        driver.get(eurowingsUrl);
        assertEquals(eurowingsUrl, driver.getCurrentUrl());
        eurowingsPage.confirmCookies();
        eurowingsPage.selectEnglish();
        log.info("Page opened correctly");
    }

    @When("I click on Flight route radio button")
    public void iClickOnFlightRouteRadioButton() {
        log.info("When I click on Flight route radio button");
        eurowingsPage.pickFlightRouteRadioButton();
        assertTrue(eurowingsPage.getFlightRouteRadioButton().isSelected());
    }

    @Then("I see correct Flight route inputs")
    public void iSeeCorrectFlightRouteInputs() {
        log.info("Then I see correct Flight route inputs");
        assertEquals("Departure airport", eurowingsPage.getDepartureAirportInputText().getText());
        assertEquals("Destination airport", eurowingsPage.getDestinationAirportInputText().getText());
        assertTrue(eurowingsPage.getDepartureDateInput().isDisplayed());
    }

    @When("I pick departure airport as {string}")
    public void iPickDepartureAirport(String departureAirport) {
        log.info("When I pick departure airport as " + departureAirport);
        eurowingsPage.pickDepartureAirport(departureAirport.trim());
    }

    @And("I pick destination airport as {string}")
    public void iPickDestinationAirport(String destinationAirport) {
        log.info("When I pick destination airport as " + destinationAirport);
        eurowingsPage.pickDestinationAirport(destinationAirport.trim());
    }

    @And("I pick departure date as {string}")
    public void iPickDepartureDate(String departureDate) {
        log.info("When I pick departure date as " + departureDate);
        eurowingsPage.pickDepartureDate(departureDate.trim());
    }

    @And("I confirm to show flight status")
    public void iConfirmToShowFlightStatus() {
        log.info("When I confirm to show flight status");
        eurowingsPage.showFlightStatus();
    }

    @Then("I see list of my searched flight")
    public void iSeeListOfMySearchedFlight() {
        log.info("Then I see list of my searched flight");
        assertEquals(eurowingsPage.checkPickedDate(), eurowingsPage.checkSearchedDate());
        log.info("You have " + eurowingsPage.checkNumberOfFlightOptions() + " possible flight options.");
    }

    @When("I click on Flight number radio button")
    public void iClickOnFlightNumberRadioButton() {
        log.info("When I click on Flight number radio button");
        eurowingsPage.pickFlightNumberRadioButton();
        assertTrue(eurowingsPage.getFlightNumberRadioButton().isSelected());
    }

    @Then("I see correct Flight number inputs")
    public void iSeeCorrectFlightNumberInputs() {
        log.info("Then I see correct Flight number inputs");
        assertTrue(eurowingsPage.getFlightNumberInput().isDisplayed());
        assertTrue(eurowingsPage.getDepartureDateInput().isDisplayed());
    }

    @When("I input flight number as {string}")
    public void iInputFlightNumber(String flightNumber) {
        log.info("When I input flight number as " + flightNumber);
        eurowingsPage.enterFlightNumber(flightNumber.trim());
    }

    @Then("I see no flights")
    public void iSeeNoFlights() {
        log.info("Then I see no flights");
        assertEquals("Unfortunately, we could not find any results for your search.", eurowingsPage.getNoResultsInfo().getText());
    }
}
