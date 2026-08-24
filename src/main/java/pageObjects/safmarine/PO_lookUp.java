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

public class PO_lookUp extends Page{
	
	public PO_lookUp(WebDriver driver) { 
		super(driver);
		PageFactory.initElements(driver, this); }
	
    HashMap<String,String> mapData;
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
    @FindBy(xpath = "//a[@href='javascript:void(0)' and contains (text(), 'Point-to-Point')]")
    protected WebElement sub2_pointToPoint;
    @FindBy(xpath = "//a[@href='javascript:void(0)' and contains (text(), 'Port Call')]")
    protected WebElement sub2_portCall;
    @FindBy(xpath = "//a[@href='javascript:void(0)' and contains (text(), 'Vessel Schedule')]")
    protected WebElement sub2_vesselSchedule;
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

    public HashMap<String,String> navigateToScheduleType(HashMap<String,String> mapData) {
        waitForElementTobeClickable(menu_lookUp);
        menu_lookUp.click();
        sub1_Schedule.click();

        if(sub2_pointToPoint.getText().contains(mapData.get("ScheduleType")) == true){
            sub2_pointToPoint.click();
        }else if(sub2_portCall.getText().contains(mapData.get("ScheduleType")) == true){
            sub2_portCall.click();
        }else {sub2_vesselSchedule.click();
        }
        return mapData;
    }
   
    public void updateP2P_Schedule(HashMap<String, String> mapTestData) {

        userAction(p2p_fromCity,mapTestData.get("FromCity"));
        setRadioButton(p2p_fromHaulage, mapTestData.get("FromHaulage"));
        userAction(p2p_toCity,mapTestData.get("ToCity"));
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
        clickWithJavaScriptExecutor(p2p_btnSubmit);
    }

    public void updatePC_Schedule(HashMap<String, String> mapTestData) {

        System.out.println("In Port Call Schedule");
        userAction(pc_fromCountry, mapTestData.get("CountryName"));
        userAction(pc_fromPort,mapTestData.get("PortName"));
        userAction(pc_fromDate,mapTestData.get("DateFrom"));
        pc_btnSearch.click();
    }

    public void updateVS_Schedule(HashMap<String, String> mapTestData) {

        userAction(vs_vesselName, mapTestData.get("VesselName"));
        System.out.println(mapTestData.get("VesselName"));
        userAction(vs_fromDate, mapTestData.get("DateFrom"));

        vs_searchBtn.click();
    }

    public void readPopulated_ScheduleData(){

        int size = resultList.size();
        System.out.println("Total no. of vessels = " + size);
    }

    public WebElement setRadioButton(List<WebElement> list, String strRefValue) {

        for (WebElement element : list) {
            System.out.println("Checking element with value " + element.getAttribute("value") + "/" + strRefValue);
            if (element.getAttribute("value").equals(strRefValue)) {
                System.out.println("Selecting element with value " + element.getAttribute("value") + "/" + strRefValue);
                clickWithJavaScriptExecutor(element);
                return element;
            }
        }
        return null;
    }

    public void set_dropdown(List<WebElement> list, String strValue) {

        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(strValue)){
                element.sendKeys(strValue);
                element.click();
            }
        }
    }

    public WebElement waitForElementTobeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void clickWithJavaScriptExecutor(WebElement element) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", element);
    }

    public void userAction(WebElement element, String strValue) {

        waitForElementTobeClickable(element);
        Actions act = new Actions(driver);
        act.moveToElement(element)
                .click()
                .sendKeys(strValue).pause(3000)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
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
