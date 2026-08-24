package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import customAnnotations.RetryCountIfFailed;

public class CustomRetryListner implements IRetryAnalyzer {

    private int counter = 0;

    @Override
    public boolean retry(ITestResult result) {
        // Check if the test method has the RetryCountIfFailed annotation
        RetryCountIfFailed annotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(RetryCountIfFailed.class);
        
        // Based on the value of annotation see if test needs to be rerun
        if (annotation != null && counter < annotation.value()) {
            counter++;
            System.out.println("------------------ Re-trying TestCase: " + result.getName() 
                + " [Attempt " + counter + " of " + annotation.value() + "] ------------------");
            return true;
        }
        return false;
    }
}
