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
        log.info("Starting step: I am on Eurowings page");
        String eurowingsUrl = "https://www.eurowings.com/en/information/at-the-airport/flight-status.html";
        driver.get(eurowingsUrl);
        assertEquals(eurowingsUrl, driver.getCurrentUrl());
        eurowingsPage.confirmCookies();
        eurowingsPage.selectEnglish();
        log.info("Page opened correctly");
    }

    @When("I click on Flight route radio button")
    public void iClickOnFlightRouteRadioButton() {
        log.info("Starting step: I click on Flight route radio button");
        eurowingsPage.pickFlightRouteRadioButton();
        assertTrue(eurowingsPage.getFlightRouteRadioButton().isSelected());
    }

    @Then("I see correct Flight route inputs")
    public void iSeeCorrectFlightRouteInputs() {
        log.info("Starting step: I see correct Flight route inputs");
        assertEquals("Departure airport", eurowingsPage.getDepartureAirportInputText().getText());
        assertEquals("Destination airport", eurowingsPage.getDestinationAirportInputText().getText());
        assertTrue(eurowingsPage.getDepartureDateInput().isDisplayed());
    }

    @When("I pick {string} as departure airport")
    public void iPickDepartureAirport(String departureAirport) {
        log.info("Starting step: I pick " + departureAirport + " as departure airport");
        eurowingsPage.pickDepartureAirport(departureAirport);
    }

    @And("I pick {string} as destination airport")
    public void iPickDestinationAirport(String destinationAirport) {
        log.info("Starting step: I pick BER as destination airport");
        eurowingsPage.pickDestinationAirport(destinationAirport);
    }

    @And("I pick {string} as departure date")
    public void iPickDepartureDate(String departureDate) {
        log.info("Starting step: I pick %date% as departure date");
        eurowingsPage.pickDepartureDate(departureDate);
    }

    @And("I confirm to show flight status")
    public void iConfirmToShowFlightStatus() {
        log.info("Starting step: I confirm to show flight status");
        eurowingsPage.showFlightStatus();
    }

    @Then("I see list of my searched flight")
    public void iSeeListOfMySearchedFlight() {
        log.info("Starting step: I see status for searched flight");
        assertEquals(eurowingsPage.checkPickedDate(), eurowingsPage.checkSearchedDate());
        log.info("You have " + eurowingsPage.checkNumberOfFlightOptions() + " possible flight options.");
    }

    @When("I click on Flight number radio button")
    public void iClickOnFlightNumberRadioButton() {
        log.info("Starting step: I click on Flight number radio button");
        eurowingsPage.pickFlightNumberRadioButton();
        assertTrue(eurowingsPage.getFlightNumberRadioButton().isSelected());
    }

    @Then("I see correct Flight number inputs")
    public void iSeeCorrectFlightNumberInputs() {
        log.info("Starting step: I see correct Flight number inputs");
        assertTrue(eurowingsPage.getFlightNumberInput().isDisplayed());
        assertTrue(eurowingsPage.getDepartureDateInput().isDisplayed());
    }

    @When("I input {string} flight number")
    public void iInputFlightNumber(String flightNumber) {
        log.info("Starting step: I input XXX flight number");
        eurowingsPage.enterFlightNumber(flightNumber);
    }

    @Then("I see no flights")
    public void iSeeNoFlights() {
        log.info("Starting step: I see no flights");
        assertEquals("Unfortunately, we could not find any results for your search.", eurowingsPage.getNoResultsInfo().getText());
    }
}
