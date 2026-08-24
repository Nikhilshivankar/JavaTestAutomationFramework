package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

import libraries.PropertyReader;

public class ITestListener_Implimentation extends ExtentReportListener implements ITestListener {

    public PropertyReader props;
    private static ExtentReports extentReports;
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("on starting the test: " + result.getName());
        if (extent != null) {
            test = extent.createTest(result.getTestClass().getRealClass().getSimpleName() + " - " + result.getMethod().getMethodName());
            String className = result.getTestClass().getRealClass().getName();
            if (className.contains("api_TESTING")) {
                test.assignCategory("API Testing");
            } else if (className.contains("ui_TESTING")) {
                test.assignCategory("UI Testing");
            } else {
                test.assignCategory("Reference Suite");
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("SCENARIO PASSED: " + result.getName());
        if (test != null) {
            test.pass("Test passed successfully");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("SCENARIO FAILED: " + result.getName());
        if (test != null) {
            test.fail("Test failed");
            if (result.getThrowable() != null) {
                test.fail(result.getThrowable());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("SCENARIO SKIPPED: " + result.getName());
        if (test != null) {
            test.skip("Test skipped");
            if (result.getThrowable() != null) {
                test.skip(result.getThrowable());
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Execution Started on Application: " + context.getName());
        props = new PropertyReader();
        String reportLoc = "testResults/Extent_Report.html";
        try {
            reportLoc = props.getProperty("extentReportLocation");
        } catch (Exception e) {
            System.out.println("Could not read 'extentReportLocation' from configuration. Using default.");
        }
        extentReports = setUp(reportLoc);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Execution Completed: " + context.getName());
        tearDown();
        System.out.println("Generating Report");
    }
}
