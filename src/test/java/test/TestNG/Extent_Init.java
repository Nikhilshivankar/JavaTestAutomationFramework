package test.TestNG;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extent_Init {
    
    public ExtentReports extReport;
    public ExtentTest extTest;
    
    @BeforeClass
    public void startReport() {
        System.out.println("in Extent_Init / @BeforeClass / startTest");
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extentReports/EXTENTreport.html");
        extReport = new ExtentReports();
        extReport.attachReporter(spark);
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        System.out.println("in Extent_Init / @BeforeMethod / setup");
        System.out.println("Current test name = " + result.getMethod().getMethodName());
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println("in Extent_Init / @AfterMethod / tearDown");
        
        if (extTest == null) {
            return;
        }

        if (result.getStatus() == ITestResult.SUCCESS) {
            extTest.log(Status.PASS, "TestCase passed is " + result.getName());
        } else if (result.getStatus() == ITestResult.FAILURE) {
            extTest.log(Status.FAIL, "TestCase failed is " + result.getName());
            if (result.getThrowable() != null) {
                extTest.fail(result.getThrowable());
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            extTest.log(Status.SKIP, "TestCase skipped is " + result.getName());
        }
    }
    
    @AfterTest
    public void endReport() {
        System.out.println("in Extent_Init / @AfterClass / endreport");
        if (extReport != null) {
            extReport.flush();
        }
    }
}