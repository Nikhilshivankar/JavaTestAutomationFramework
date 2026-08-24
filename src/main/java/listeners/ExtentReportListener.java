package listeners;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener {

    public static ExtentSparkReporter spark = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    
    public static ExtentReports setUp(String reportLocation) {
        spark = new ExtentSparkReporter(reportLocation);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Automation ExtentReport");
        spark.config().setTheme(Theme.DARK);
        
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "Test Automation Framework");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        
        return extent;
    }
    
    public static void testStepHandle(String testStatus, WebDriver driver, ExtentTest exTest, Throwable throwable) {
        switch (testStatus.toUpperCase()) {
            case "FAIL":
                exTest.fail(MarkupHelper.createLabel("Scenario step is failed : ", ExtentColor.RED));
                if (throwable != null) {
                    exTest.fail(throwable);
                }
                break;
                
            case "PASS":
                exTest.pass(MarkupHelper.createLabel("Scenario step is passed : ", ExtentColor.GREEN));
                break;
            
            case "ERROR":
                exTest.fail(MarkupHelper.createLabel("Scenario step has thrown error : ", ExtentColor.INDIGO));
                if (throwable != null) {
                    exTest.fail(throwable);
                }
                break;
                
            default:
                break;
        }
    }

    public static void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}
