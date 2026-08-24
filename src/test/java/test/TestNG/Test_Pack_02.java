package test.TestNG;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import customAnnotations.RetryCountIfFailed;
import libraries.ApplicationFactory;
import libraries.Util_Excel;
import listeners.CustomRetryListner;
import libraries.ApplicationFactory.Safmarine;

public class Test_Pack_02 {

	HashMap<String,String> testData;
	Safmarine safPages ;
	@BeforeClass
	public void getPrerequisits() throws Exception {
	
		Util_Excel.openExcel_TestData();
		testData = Util_Excel.getMapTestData("TC_005");
		ApplicationFactory apps = new ApplicationFactory();
		
		/*
		 * safPages= apps.getNewInstance_Safmarine("https://my.safmarine.com");
		 * safPages.getHomePage().checkLoadCondition();
		 * safPages.getHomePage().checkExitCondition();
		 * safPages.getHomePage().navigateToScheduleType(testData);
		 */	}


//	//@Test(dataProvider= "portCall")
//	@Test
//	public void test_DataProvider(String countryName, String portName) {
//		
//		safPages.getPortCallPage().updatePC_Schedule2(countryName, portName);
//		
//	}
//	
//	@DataProvider(name = "portCall")
//	public Object[][] vesselData(){
//		return new Object[][]{{"India","Chennai"},{"India","Mundra"},{"India","Visakhapatnam"},{"India","Kolkata"},{"India","Haldia"}};
//		
//	}
//	
	
	//@Test(retryAnalyzer= CustomRetryListner.class)
	@Test
	public void test_008() {
		
		System.out.println("-------------");
		AssertJUnit.assertEquals("Nikhil", "Nikhi");
		
	}
	
	
	@Test
	@RetryCountIfFailed(5)
	
	public void test_006() {
		
		AssertJUnit.assertEquals("Nikhil", "Nikhi");
	}
	
	@Test
	public void test() {
		
		System.out.println("--------------TestCase-----Test-------------");
		AssertJUnit.assertEquals("Nikhil", "Nikhil");
		
	}
	
	
	
}
