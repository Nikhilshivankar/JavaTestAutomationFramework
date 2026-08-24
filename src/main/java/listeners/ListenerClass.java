package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("I am in onTestStart method: " + getMethodName(result) + " Start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("I am in onTestSuccess method: " + getMethodName(result) + " Succeed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("I am in onTestFailure method: " + getMethodName(result) + " Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("I am in onTestSkipped method: " + getMethodName(result) + " Skipped");        
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("I am in onStart method: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("I am in onFinish method: " + context.getName());        
    }

    public static String getMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }
}
