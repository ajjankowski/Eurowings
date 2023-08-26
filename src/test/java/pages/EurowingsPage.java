package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class EurowingsPage {

    private static WebDriver driver;

    public EurowingsPage(WebDriver driver) {
        EurowingsPage.driver = driver;
        PageFactory.initElements(EurowingsPage.driver, this);
    }

    @FindBy(xpath = "//button[@class='cookie-consent--cta-accept a-cta a-cta-prio1']")
    private WebElement cookieConfirm;

    @FindBy(xpath = "//div[@id='country-switch']/child::div[2]")
    private WebElement selectedLanguage;

    @FindBy(xpath = "//button[@class='o-collapse-header__trigger o-country-switch__toggle-icon o-collapse-trigger-directive']")
    private WebElement selectLanguageArrow;

    @FindBy(xpath = "//div[@class='o-country-switch__country' and text()='Deutsch']")
    private WebElement englishLanguage;

    @FindBy(xpath = "//input[@name='search-method'][1]")
    private WebElement flightRouteRadioButton;

    @FindBy(xpath = "(//input[@name='search-method'])[2]")
    private WebElement flightNumberRadioButton;

    @FindBy(xpath = "//input[@class='a-input-text__input m-form-mask__input-field a-input-text__input--deco-icon']")
    private WebElement flightNumberInput;

    @FindBy(xpath = "(//span[@class='o-compact-search__cta-button-floating-label'])[1]")
    private WebElement departureAirportInputText;

    @FindBy(xpath = "(//button[@class='o-compact-search__cta-button-button'])[1]")
    private WebElement departureAirportInput;

    @FindBy(xpath = "//input[@id='station-select-200-input']")
    private WebElement departureAirportListInput;

    @FindBy(xpath = "(//span[@class='o-compact-search__cta-button-floating-label'])[2]")
    private WebElement destinationAirportInputText;

    @FindBy(xpath = "(//button[@class='o-compact-search__cta-button-button'])[2]")
    private WebElement destinationAirportInput;

    @FindBy(xpath = "//input[@id='station-select-201-input']")
    private WebElement destinationAirportListInput;

    @FindBy(xpath = "//input[@class='a-input-text__input a-input-text__input--deco-icon']")
    private WebElement departureDateInput;

    @FindBy(xpath = "//button[@class='a-cta a-cta-prio1']")
    private WebElement showFlightStatusButton;

    @FindBy(xpath = "//button[@class='o-search-flight-status__date-navigation__date o-search-flight-status__date-navigation__date--active o-search-flight-status__date-navigation__date--align-center']")
    private WebElement dateOfCurrentSearch;

    @FindBy(xpath = "//div[@class='m-fieldset-text m-fieldset-text--has-float-label']")
    private WebElement pickedDate;

    @FindBy(xpath = "//div[@class='o-card-component__section--no-padding o-card-component__content']")
    private List<WebElement> searchedFlightOptions;

    @FindBy(xpath = "//h2[@class='a-headline a-headline--h4']")
    private WebElement noResultsInfo;

    public WebElement getFlightRouteRadioButton() {
        return flightRouteRadioButton;
    }

    public WebElement getFlightNumberRadioButton() {
        return flightNumberRadioButton;
    }

    public WebElement getDepartureAirportInputText() {
        return departureAirportInputText;
    }

    public WebElement getDestinationAirportInputText() {
        return destinationAirportInputText;
    }

    public WebElement getFlightNumberInput() {
        return flightNumberInput;
    }

    public WebElement getDepartureDateInput() {
        return departureDateInput;
    }

    public WebElement getNoResultsInfo() {
        return noResultsInfo;
    }

    public WebElement getDateRadioButton(String dateValue) {
        return driver.findElement(By.xpath("//input[@value='" + dateValue + "']"));
    }

    public void confirmCookies() {
        if (cookieConfirm.isDisplayed()) {
            cookieConfirm.click();
        }
    }

    public void selectEnglish() {
        if (!Objects.equals(selectedLanguage.getText(), "English")) {
            selectLanguageArrow.click();
            englishLanguage.click();
        }
    }

    public void pickFlightRouteRadioButton() {
        if (!flightRouteRadioButton.isSelected()) {
            flightRouteRadioButton.click();
        }
    }

    public void pickFlightNumberRadioButton() {
        if (!flightNumberRadioButton.isSelected()) {
            flightNumberRadioButton.click();
        }
    }

    public void pickDepartureAirport(String departureAirport) {
        departureAirportInput.click();
        departureAirportListInput.click();
        departureAirportListInput.sendKeys(departureAirport);
        departureAirportListInput.sendKeys(Keys.ENTER);

    }

    public void pickDestinationAirport(String destinationAirport) {
        destinationAirportInput.click();
        destinationAirportListInput.click();
        destinationAirportListInput.sendKeys(destinationAirport);
        destinationAirportListInput.sendKeys(Keys.ENTER);
    }

    public void pickDepartureDate(String departureDate) {
        departureDateInput.click();
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        if (departureDate.toLowerCase().trim().equals("today")) {
            getDateRadioButton(date).click();
        } else if (departureDate.toLowerCase().trim().equals("tomorrow")) {
            String tomorrowDate = date.substring(0, date.length() - 1) + (char) (date.charAt(date.length() - 1) + 1);
            getDateRadioButton(tomorrowDate).click();
        } else {
            throw new RuntimeException("Wrong data input -> pick \"today\" or \"tomorrow\".");
        }
    }

    public void showFlightStatus() {
        showFlightStatusButton.click();
    }

    public String checkSearchedDate() {
        return dateOfCurrentSearch.getText().split("/")[0].split(" ")[1];
    }

    public String checkPickedDate() {
        return pickedDate.getAttribute("eventmodelvalue").split("/")[0];
    }

    public int checkNumberOfFlightOptions() {
        return searchedFlightOptions.size();
    }

    public void enterFlightNumber(String flightNumber) {
        flightNumberInput.sendKeys(flightNumber);
    }
}
