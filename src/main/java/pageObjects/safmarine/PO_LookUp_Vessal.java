package pageObjects.safmarine;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import libraries.Page;

import java.sql.Driver;
import java.util.HashMap;
import java.util.List;

public class PO_LookUp_Vessal extends Page {

	public PO_LookUp_Vessal(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	HashMap<String, String> mapData;

	@FindBy(xpath = "//*[@id='ss-nav-title-0' and contains (text(), 'Lookup')]")
	protected WebElement menu_lookUp;
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Schedules')]")
	protected WebElement sub1_Schedule;
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Find a Price')]")
	protected WebElement sub1_FindPrice;

	
	// VESSEL SCHEDULE WEBELEMNET below
	
	@FindBy(xpath = "//input[@id='vesselName']")
	protected WebElement vs_vesselName;
	@FindBy(xpath = "//input[@id='psuedo-date-input-undefined']")
	protected WebElement vs_fromDate;
	@FindBy(xpath = "//button[@id='searchVesselSchedules']")
	protected WebElement vs_searchBtn;
	
	@FindBy(xpath = "//*[contains (text(), Vessel/Voyage)]")
	List<WebElement> resultList;

	public void updateVS_Schedule(HashMap<String, String> mapTestData) {

		userAction1(vs_vesselName, mapTestData.get("VesselName"));
		System.out.println(mapTestData.get("VesselName"));
		vs_fromDate.clear();
		userAction1(vs_fromDate, mapTestData.get("DateFrom"));
		vs_fromDate.submit();
		//vs_searchBtn.click();
	}

	public void readPopulated_ScheduleData() {

		int size = resultList.size();
		System.out.println("Total no. of vessels = " + size);
	}

	@Override
	public void checkLoadCondition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkExitCondition() {
		// TODO Auto-generated method stub
		
	}

}
