package test.TestNG;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class Test_ExtentReport extends Extent_Init {

    @Test
    public void test_001() {
        extTest = extReport.createTest("test_001");
        Assert.assertTrue(true);
        extTest.log(Status.PASS, "TestCase pass is passed_Test");
    }
    
    @Test
    public void test_002() {
        extTest = extReport.createTest("test_002");
        Assert.assertTrue(false);
        extTest.log(Status.FAIL, "TestCase pass is failed_Test");
    }
    
    @Test
    public void test_003() {
        extTest = extReport.createTest("test_003");
        throw new SkipException("SKIPPING ----- This testcase is not ready");
    }
}
