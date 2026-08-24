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

public class PO_LookUp_P2P extends Page {

	public PO_LookUp_P2P(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	HashMap<String, String> mapData;
	boolean bValue;
	
	@FindBy(xpath = "//*[@id='ss-nav-title-0' and contains (text(), 'Lookup')]")
	protected WebElement menu_lookUp;
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Schedules')]")
	protected WebElement sub1_Schedule;
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Find a Price')]")
	protected WebElement sub1_FindPrice;

	@FindBy(xpath = "//*[@class = 'stages__item' and @id = '0']")
	protected WebElement haulageStage_inlandExp;

	@FindBy(xpath = "//*[@class = 'stages__item' and @id = '1']")
	protected WebElement haulageStage_ocean;

	@FindBy(xpath = "//*[@class = 'stages__item' and @id = '2']")
	protected WebElement haulageStage_inlandImp;

	@FindBy(xpath = "//*[@id='oversized_checkbox']")
	protected WebElement checkBox_Oversize;

	@FindBy(xpath = "//*[@id=\"findprice_submit_1\" and contains(text(), 'Continue')]")
	protected WebElement btnSubmit;

	
	@FindBy(xpath = "//a[@xpath ='1' and contains(.,'Point-to-Point')]")
	protected WebElement sub2_pointToPoint;
	
	@FindBy(xpath = "//*[@id='originLocation']")
	protected WebElement p2p_fromCity;
	@FindBy(xpath = "//*[@name = 'originServiceMode']")
	List<WebElement> p2p_fromHaulage;
	@FindBy(xpath = "//*[@id='destinationLocation']")
	protected WebElement p2p_toCity;
	@FindBy(xpath = "//*[@name = 'destinationServiceMode']")
	List<WebElement> p2p_toHaulage;
	@FindBy(xpath = "//*[@class='font--default--bold button__text uppercase' and contains(text(), 'Advanced Search')]")
	protected WebElement p2p_dropElement;
	@FindBy(xpath = "//input[@id='psuedo-date-input-undefined']")
	protected WebElement p2p_datePicker;
	@FindBy(xpath = "//*[@name = 'shippingDateMode']")
	List<WebElement> p2p_sortDeparture;
	@FindBy(xpath = "//*[@name='shippingDateWeeks']")
	List<WebElement> p2p_radBtn_noWeeks;
	@FindBy(xpath = "//*[@id = 'containerType']")
	List<WebElement> List_containerType;
	@FindBy(xpath = "//input[@id='temperatureControl']")
	protected WebElement p2p_checkBox;
	@FindBy(xpath = "//input[@id='vesselFlag']")
	List<WebElement> List_drpVesselFlag;
	@FindBy(xpath = "//button[@type='submit' and contains(text(), Search)]")
	protected WebElement p2p_btnSubmit;
	@FindBy(xpath = "//input[@id='country']")
	protected WebElement pc_fromCountry;
	@FindBy(xpath = "//input[@id='port']")
	protected WebElement pc_fromPort;
	@FindBy(xpath = "//*[@id='psuedo-date-input-undefined']")
	protected WebElement pc_fromDate;
	@FindBy(xpath = "//button[@id='searchPortCalls']")
	protected WebElement pc_btnSearch;
	@FindBy(xpath = "//input[@id='vesselName']")
	protected WebElement vs_vesselName;
	@FindBy(xpath = "//input[@id='psuedo-date-input-undefined']")
	protected WebElement vs_fromDate;
	@FindBy(xpath = "//button[@id='searchVesselSchedules']")
	protected WebElement vs_searchBtn;
	@FindBy(xpath = "//*[contains (text(), Vessel/Voyage)]")
	List<WebElement> resultList;
	@FindBy(xpath="//p[@class='margin-bottom--small print--remove-margin']")
	protected WebElement resultStatement;
	
	
	public void updateP2P_Schedule(HashMap<String, String> mapTestData) {

		userAction1(p2p_fromCity, mapTestData.get("FromCity"));
		setRadioButton(p2p_fromHaulage, mapTestData.get("FromHaulage"));
		userAction1(p2p_toCity, mapTestData.get("ToCity"));
		setRadioButton(p2p_toHaulage, mapTestData.get("ToHaulage"));

		p2p_dropElement.click();

		if (p2p_datePicker.isDisplayed() == true) {
			p2p_datePicker.sendKeys(mapTestData.get("FromDate"));
		}

		setRadioButton(p2p_sortDeparture, mapTestData.get("SortBy"));
		setRadioButton(p2p_radBtn_noWeeks, mapTestData.get("No_Week"));

		set_dropdown(List_containerType, mapTestData.get("ContainerType"));

		if (mapTestData.get("ContainerType").contains("Reefer") == true) {
			p2p_checkBox.click();
		}
		
		set_dropdown(List_drpVesselFlag, mapTestData.get("VesselFlag"));
		
		p2p_btnSubmit.click();
		
		/*bValue = resultStatement.isDisplayed();
		
		System.out.println("-----------------"+ bValue + "-----------------");
		return bValue;*/
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
