package pageObjects.clearTrip;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import libraries.Page;

public class PO_CT_HomePage extends Page {

	public PO_CT_HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public void checkLoadCondition() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkExitCondition() {
		// TODO Auto-generated method stub

	}

	// --------------------------------HOMEPAGE----------------------//
	@FindBy(xpath = "//span[contains(., 'Flights')]")
	private WebElement tab_Flights;
	@FindBy(xpath="//*[@name=\"trip_type\"]")
	private WebElement list_RadBtn_TripType;
	@FindBy(xpath = "//*[@title='One way']")
	private WebElement btn_OneWay;
	@FindBy(xpath = "//*[@title='Round Trip']")
	private WebElement btn_RoundTrip;
	@FindBy(xpath = "//*[@title='MultiCity']")
	private WebElement btn_MultiCity;
	@FindBy(xpath = "//*[@id='FromTag']")
	private WebElement fromCity;
	@FindBy(xpath = "//input[@id='ToTag']")
	private WebElement toCity;
	@FindBy(xpath = "//input[@title='Depart date']")
	private WebElement departDate;
	@FindBy(xpath = "//input[@title='Return date']")
	private WebElement arrivalDate;
	@FindBy(xpath = "//select[@name='adults']")
	private WebElement dropdownAdult;
	@FindBy(xpath = "//select[@name='childs']")
	private WebElement dropdownChildrens;
	@FindBy(xpath = "//select[@name='infants']")
	private WebElement dropdownInfants;
	@FindBy(xpath = "//strong[contains(.,'More options:')]")
	private WebElement moreOptions;
	@FindBy(xpath = "//select[@name='class']")
	private WebElement flightClass;
	@FindBy(xpath = "//input[@name='airline']")
	private WebElement flightNameInput;
	@FindBy(xpath = "//input[@value='Search flights']")
	private WebElement tab_SearchFlights;
	@FindBy(xpath = "//input[@class='tripType']")
	private WebElement list_rdBtn_tripType;

	// --------------------------------ResultPage----------------------//
	@FindBy(xpath ="//*/th[@class='price']")
	private List<WebElement> list_Flights;
	@FindBy(xpath = "(//button[@class='booking'])[1]")
	private WebElement btn_First_Booking;
	@FindBy(xpath = "//a[@data-sort='price']")
	private WebElement priceSorter;
	@FindBy(xpath ="//div[@data-block-type='stops']")
	private WebElement filter_Stops;
	
	@FindBy(xpath="//li[input[@name='stops']]/label")
	private List<WebElement> list_Stops;
	
	@FindBy(xpath ="//div[@data-block-type='price']")
	private WebElement filter_Price;
	@FindBy(xpath ="//div[@data-block-type='departureTime']")
	private WebElement filter_DepartureTime;
	@FindBy(xpath ="//div[@data-block-type='airlines']")
	private WebElement filter_Airlines;
	
	@FindBy(xpath ="//div[@data-block-type='tripDuration']")
	private WebElement filter_TripDuration;
	
	// -------------------------------Itinary---------------------------//

	@FindBy(xpath = "//input[@value='Continue booking']")
	private WebElement btn_ContinueBooking;

	@FindBy(xpath = "//input[@type='checkbox'][contains(@id,'confirm')]")
	private WebElement chkBox_ICICI;

	public void setFilters(WebElement element) {
		if(element.getAttribute("class").contains("closed")) {
			element.click();	
		}/*
		else if(filter_Price.getAttribute("class").contains("closed")) {
			filter_Price.click();	
		}
		else if(filter_DepartureTime.getAttribute("class").contains("closed")) {
			filter_DepartureTime.click();	
		}
		else if(filter_Airlines.getAttribute("class").contains("closed")) {
			filter_Airlines.click();	
		}
		else if(filter_TripDuration.getAttribute("class").contains("closed")) {
			filter_TripDuration.click();	
		}
	*/	
	}
	public List<WebElement> setValue(List<WebElement> list, String str){
		
		String atrName = "background-color";
		
		WebElement element = list.get(2);
		String unselectedColor = "255" ; // element.getCssValue(atrName);
		int a = 1;

		for(WebElement e : list) {
			
			System.out.println(e.getCssValue(atrName));
			
			String currentColor = e.getCssValue(atrName);
			String txt = e.getText();
			
			if (currentColor.contains(unselectedColor)) {
				
				if((txt.contains(str))) {
					System.out.println("Selecting same colored " + txt + "   -- " + currentColor + " = " + unselectedColor);
					e.click();
				}else {
					System.out.println("Bypassed same colored " + txt + "   -- " + currentColor + " = " + unselectedColor);
				}
			}else {
				if((txt.contains(str))) {
					System.out.println("Bypassed " + txt + "   -- " + currentColor + " = " + unselectedColor);					
				}else {
					System.out.println("Deselecting " + txt + "   -- " + currentColor + " = " + unselectedColor);
					e.click();
				}
			}
		
		}
		return list;
	}
	
	public void userInput(WebElement ele, String str) {
		
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().sendKeys(str).pause(5000).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
	}

	public HashMap<String, String> searchFlights_OneWay(HashMap<String, String> mapData) {

		btn_OneWay.click();
		
		waitForElementTobeClickable(fromCity);
		
		System.out.println("--------entering from and to cities----------");
		
		userInput(fromCity,mapData.get("FromCity"));	
		
		waitForElementTobeClickable(toCity);
		
		userInput(toCity,mapData.get("ToCity"));
		
		System.out.println("--------entered from and to cities----------");
		
		departDate.sendKeys(mapData.get("DepartureDate").toString());

		set_dropdown(dropdownAdult, mapData.get("Adults"));
		set_dropdown(dropdownChildrens, mapData.get("Children"));
		set_dropdown(dropdownInfants, mapData.get("Infants"));
	
		JavascriptExecutor js = (JavascriptExecutor) driver;		
        js.executeScript("window.scrollBy(0,500)");
        
		moreOptions.click();
		set_dropdown(flightClass, mapData.get("FlightClass"));	    
		tab_SearchFlights.click();

		return mapData;

	}

	public HashMap<String, String> modifySearch_and_bookFlight(HashMap<String, String> mapData) {
		int i =1,flightPrice;
		
		setFilters(filter_Stops);
		
//		Alert a = driver.switchTo().alert();
//		System.out.println("Alert = " + a.getText());
//		a.dismiss();
		
		setValue(list_Stops, mapData.get("Stops"));
		
		/*
		waitForElementTobeClickable(priceSorter);
		if(priceSorter.getAttribute("class").equals("current sortDes")) { priceSorter.click();}
		
		List<Integer> listPrice = new ArrayList<Integer>();
		for(WebElement e : list_Flights) {
			String strPrice,price;
			System.out.println( "flight no = " + i + "  " +e.getText().toString());
			strPrice=e.getText().toString();
			price = strPrice.substring(3).replace(",","");
			flightPrice = Integer.parseInt(price);
			listPrice.add(flightPrice);
			i++;
		}
		//System.out.println("Cheapest flight price is " + Collections.min(listPrice));
		
		btn_First_Booking.click();
		chkBox_ICICI.click();
		btn_ContinueBooking.click();
*/		
		
		return mapData;
	}
	

	
}
