package web.pageobjects;

import genericwrappers.GenericWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends GenericWrapper {

	public HomePage(WebDriver driver){

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
//Testing-1 Checking for the correction//
		//For your reference//

		//Testing 2 Teaching Purpose//

	}

	@FindBy(xpath = "//span[@data-cy='closeModal']")
	WebElement CROSS_ICON;

	@FindBy(xpath = "//li[@class='menu_Flights']")
	WebElement FLIGHTS;

	@FindBy(xpath = "//li[@class='menu_Hotels']")
	WebElement HOTELS;

	@FindBy(xpath = "//li[contains(@class,'menu_Homestays')]")
	WebElement HOMESTAYS_VILLAS;

	@FindBy(xpath = "//li[contains(@class,'menu_Holidays')]")
	WebElement HOLIDAYS_PACKAGES;

	@FindBy(xpath = "//li[contains(@class,'Trains')]")
	WebElement TRAINS;

	@FindBy(xpath = "//li[@class='menu_Buses']")
	WebElement BUSES;

	@FindBy(xpath = "//li[@class='menu_Cabs']")
	WebElement CABS;

	@FindBy(xpath = "//li[@class='menu_Visa']")
	WebElement VISA;

	@FindBy(xpath = "//li[@class='menu_Forex']")
	WebElement CARD_CURRENCY;

	@FindBy(xpath = "//li[@class='menu_TravelInsurance']")
	WebElement TRAVEL_INSURANCE;

	@FindBy(xpath = "//li[contains(@class, 'style__LocaleSettings')]")
	WebElement LANGUAGES;

	@FindBy(xpath = "//li[@class='makeFlex hrtlCenter lhMyTrips']")
	WebElement MY_TRIPS;

	public void clickOnCrossIcon(){
		clickElement(CROSS_ICON);
	}

	public void clickOnFlightsIcon(){
		clickElement(FLIGHTS);
	}

	public void clickOnHotelsIcon(){
		clickElement(HOTELS);
	}
	public void clickOnHomeStayAndVillasIcon(){
		clickElement(HOMESTAYS_VILLAS);
	}

	public void clickOnHolidayPackagesIcon(){
		clickElement(HOLIDAYS_PACKAGES);
	}

	public void clickOnTrainIcon(){
		clickElement(TRAINS);
	}
	public void clickOnBusesIcon(){
		clickElement(BUSES);
	}
	public void clickOnCabsIcon(){
		clickElement(CABS);
	}
	public void clickOnVisaIcon(){
		clickElement(VISA);
	}
	public void clickOnCardIcon(){
		clickElement(CARD_CURRENCY);
	}
	public void clickOnTravelInsuranceIcon(){
		clickElement(TRAVEL_INSURANCE);
	}
	public void clickOnLanguagesIcon(){
		clickElement(LANGUAGES);
	}
	public void clickOnTravelHistoryIcon(){
		clickElement(MY_TRIPS);
	}

}
