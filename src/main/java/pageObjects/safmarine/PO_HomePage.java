package pageObjects.safmarine;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import libraries.Page;

public class PO_HomePage extends Page{

	public PO_HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@Override
	public void checkLoadCondition() {
		
		System.out.println("This is a load condition");
	}

	@Override
	public void checkExitCondition() {
		
		System.out.println("This is a exit condition");		
	}
	
	//  MENU ITEMS------****************
    
	@FindBy(xpath = "//a[@class='ss-nav-rev__menu__item__link' and contains (text(), 'Lookup')]")
    protected WebElement menu_lookUp;
    
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Find a Price')]")
    protected WebElement menu_FindPrice;
    
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Tariff Inquiry')]")
    protected WebElement menu_TariffInquiry;
    
	@FindBy(xpath = "//*[@class=\"font-links__separators\" and contains (text(), 'Schedules')]")
    protected WebElement subMenu_Schedule;
    
	
	// SUBMENU ITEMS----------*******************
	@FindBy(xpath = "//a[@role='menuitem' and contains(.,'Point-to-Point')]")
    protected WebElement sub2_pointToPoint;
    @FindBy(xpath = "//a[@href='javascript:void(0)' and contains (., 'Port Calls')]")
    protected WebElement sub2_portCall;
    @FindBy(xpath = "//a[@href='javascript:void(0)' and contains (., 'Vessel Schedules')]")
    protected WebElement sub2_vesselSchedule;
    
    
    public HashMap<String,String> navigateToScheduleType(HashMap<String,String> mapData) {

    	System.out.println("clicking on lookUp menu");
    	
    	waitForElementTobeClickable(menu_lookUp);
    	menu_lookUp.click();
    
    	System.out.println("Navigating To Schedule Menu");
        
    	subMenu_Schedule.click();
        
    	System.out.println("Navigating To Schedule Type");
        String scheduleType = mapData.get("ScheduleType").toString();
        
        
        if(sub2_pointToPoint.getText().contains(scheduleType) == true){
            sub2_pointToPoint.click();
        }else if(sub2_portCall.getText().contains(scheduleType) == true){
            sub2_portCall.click();
        }else if(sub2_vesselSchedule.getText().contains(scheduleType) == true){
            sub2_vesselSchedule.click();
        }
        
        System.out.println("selected scheduleType is :"+ scheduleType);
        
        return mapData;
        
    }
    
  
    
    
    
    
  
}
