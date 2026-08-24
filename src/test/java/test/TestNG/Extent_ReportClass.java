package test.TestNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extent_ReportClass {

    public static ExtentReports report;
    public static ExtentTest logger;

    WebDriver driver; 
    
    @BeforeMethod
    public void setUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extentReports/EXTENTreport.html");       
        report = new ExtentReports();
        report.attachReporter(spark);
        
        logger = report.createTest(this.getClass().getSimpleName()).assignCategory("Happy_Path");
        logger.log(Status.INFO, "String Message to Log for Each Step in Test Case");
    }
    
    @AfterMethod(alwaysRun=true)
    public void TearDown_AM(ITestResult result) throws IOException {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                logger.log(Status.PASS, this.getClass().getSimpleName() + " Test Case Success and Title Verified"); 
            } else if (result.getStatus() == ITestResult.SKIP) {
                logger.log(Status.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                logger.log(Status.FAIL, this.getClass().getSimpleName() + " Test Case Failed");
                if (result.getThrowable() != null) {
                    logger.fail(result.getThrowable());
                }
            }
            report.flush();
        } catch (Throwable t) {
            logger.fail(t);
        }
    }
}
