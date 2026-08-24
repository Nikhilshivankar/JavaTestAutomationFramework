package pageObjects.safmarine;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import libraries.Page;
import libraries.Util_CSV;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PO_LookUp_PortCall extends Page {

	public PO_LookUp_PortCall(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	HashMap<String, String> mapData;
	String fileName = "Schedules - Port Calls Search Results.csv";
	String downloadPath = "src\\resources\\Download";
	String portCallResult = "Schedules - Port Calls Search Results";
	String filePath = "src\\resources\\Download\\Schedules - Port Calls Search Results.csv";

	@FindBy(xpath = "//input[@placeholder='Enter country/area name']")
	protected WebElement pc_fromCountry;

	@FindBy(xpath = "//input[@id='port']")
	protected WebElement pc_fromPort;

	@FindBy(xpath = "//input[@id='psuedo-date-input-undefined']")
	protected WebElement pc_fromDate;

	@FindBy(xpath = "//button[@id='searchPortCalls']")
	protected WebElement pc_btnSearch;

	@FindBy(xpath = "//strong[@class='font--default--bold print--font-size--small']")
	protected WebElement resultForCityCountry;

	@FindBy(xpath = "//*[@id=\"portResults\"]")
	protected List<WebElement> result_PortCall;

	@FindBy(xpath = "//div[@class='description-card description-card--schedule']")
	protected List<WebElement> vesselList;

	@FindBy(xpath = "//*/button/span[@class='icon icon-download  cancel-margin-all']")
	protected WebElement btnDownload;

	public void verify_CSVResult_and_PageResult() {

		try {
			Util_CSV.readCSV(filePath);
			validate_CSV_Data();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readPopulated_Schedule() {

		List<WebElement> list_Vessel = new ArrayList<WebElement>(vesselList);
		System.out.println("No of Available vessel : " + list_Vessel.size());
		
	}

	public void validate_CSV_Data() {

		ArrayList<String[]> arr = new ArrayList<String[]>();
		try {
			
			arr = Util_CSV.readCSV(filePath);

		} catch (IOException e) {	e.printStackTrace();    }

		for (int i = 2; i < arr.size(); i++) {

			String[] values = arr.get(i);

			String strVoyage = values[3];

			// driver.findElement(By.xPath("//div[@class='description-card
			// description-card--schedule']"//))

			String[] vesArr = getVessalDetails(vesselList.get(i - 2));

			if (values == vesArr) {

				System.out.println("validation of both results successfull.");
			} else {

				System.out.println("validation of both results failed");
			}
		}	
}

	public String[] getVessalDetails(WebElement vessalContainer) {

		By loc_Vessel = By.xpath("//dl[dd[text()='Vessel/Voyage']]//dd[3]");
		By loc_Terminal = By.xpath("//dl[dd[text()='Terminal']]/dd[3]");
		By loc_ArrivalDate = By.xpath("//dl[dd[text()='Arrival']]/dd[3]");
		By loc_Voyage = By.xpath("//dl[dd[text()='Vessel/Voyage']]/dd[4]");
		By loc_DepartureDate = By.xpath("//dl[dd[text()='Departure']]/dd[3]");
		By loc_Deadline_CY = By.xpath("//*[div[@title='Container Yard' and text()]]");
		By loc_Deadline_SI_NonAMS 
			= By .xpath("//*[div[@title='Shipping Instruction - Non Advanced Manifest Submission' and text()]]");
		By loc_Deadline_SI_AMS = By.xpath("//*[div[@title='Advanced Manifest Submission' and text()]]");
		By loc_Deadline_VGM = By.xpath("//*[div[@title='Verified Gross Mass' and text()]]");

		String vesselName = vessalContainer.findElement(loc_Vessel).getText();
		String terminalName = vessalContainer.findElement(loc_Terminal).getText();
		String arrivalDate = vessalContainer.findElement(loc_ArrivalDate).getText();
		String voyage = vessalContainer.findElement(loc_Voyage).getText();
		String departureDate = vessalContainer.findElement(loc_DepartureDate).getText();
		String deadlineCY = vessalContainer.findElement(loc_Deadline_CY).getText();
		String siNonAMS = vessalContainer.findElement(loc_Deadline_SI_NonAMS).getText();
		String siAMS = vessalContainer.findElement(loc_Deadline_SI_AMS).getText();
		String deadlineVGM = vessalContainer.findElement(loc_Deadline_VGM).getText();

		String[] arrVesselDetails = { vesselName, terminalName, arrivalDate, voyage, departureDate, deadlineCY,
				siNonAMS, siAMS, deadlineVGM };

		return arrVesselDetails;
		
		/* Vessel,Terminal,Arrival Date,Voyage,Departure Date,Deadline CY,Deadline SI -
		 * NON AMS,Deadline SI - AMS,Deadline VGM */
	}

	public boolean downloadCSV() {

		System.out.println("-------------------Now Downloading CSV file------------------");
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		
		for (int i = 0; i < dirContents.length; i++) {

			if (!dirContents[i].getName().equals(fileName)) {
				
				btnDownload.click();
				
				System.out.println("--------------Clicked on download button------------------");
				
				return true;
			} 
			}
		return false;
	}

	public void updatePC_Schedule(HashMap<String, String> mapTestData) {

		System.out.println("In Port Call Schedule");

		userAction1(pc_fromCountry, mapTestData.get("CountryName"));
		String strPC_fromPort = pc_fromPort.getText().toString();
		userAction1(pc_fromPort, mapTestData.get("PortName"));
		String strPC_fromCountry = pc_fromPort.getText().toString();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#fromDate').val('" + mapTestData.get("DateFrom") + "').change();", pc_fromDate);

		// userAction1(pc_fromDate, mapTestData.get("DateFrom"));

		WebElement offsetElement = driver.findElement(By.xpath("//a[@title='Help']"));
		Actions act = new Actions(driver);
		act.moveToElement(offsetElement).click().pause(1000).click().build().perform();
		String strActual = strPC_fromPort.concat(", ").concat(strPC_fromCountry);
		Actions act2 = new Actions(driver);
		act.moveToElement(pc_btnSearch).click().build().perform();

		System.out.println("schedule details submitted");

	}


	
	public void updatePC_Schedule2(String countryName, String portName, String date) {

		System.out.println("In Port Call Schedule");

		userAction1(pc_fromCountry, countryName);
		userAction1(pc_fromPort, portName);
		userAction1(pc_fromDate, date);

		WebElement offsetElement = driver.findElement(By.xpath("//a[@title='Help']"));
		Actions act = new Actions(driver);
		act.moveToElement(offsetElement).click().pause(1000).click().build().perform();
		Actions act2 = new Actions(driver);
		act.moveToElement(pc_btnSearch).click().build().perform();
	
		System.out.println("Port Call schedule details submitted");

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
