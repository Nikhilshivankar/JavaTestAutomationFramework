package listeners;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import helper.Helper_Log;

public class Listen_WebDriver implements WebDriverListener {

    @Override
    public void beforeGet(WebDriver driver, String url) {
        Helper_Log.add_info("Driver Listener : BeforeNavigateTo : [" + url + "]");
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        Helper_Log.add_info("Driver Listener : AfterNavigateTo : [" + url + "]");
    }

    @Override
    public void beforeClick(WebElement element) {
        Helper_Log.add_info("Driver Listener : BeforeClickOn : [" + element.toString() + "]");
    }

    @Override
    public void afterClick(WebElement element) {
        Helper_Log.add_info("Driver Listener : AfterClickOn : [" + element.toString() + "]");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        StringBuilder keys = new StringBuilder();
        if (keysToSend != null) {
            for (CharSequence cs : keysToSend) {
                keys.append(cs);
            }
        }
        Helper_Log.add_info("Driver Listener : BeforeChangeOfValue : [" + element.toString() + " --> " + keys + "]");
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        StringBuilder keys = new StringBuilder();
        if (keysToSend != null) {
            for (CharSequence cs : keysToSend) {
                keys.append(cs);
            }
        }
        Helper_Log.add_info("Driver Listener : AfterChangeOfValue : [" + element.toString() + " --> " + keys + "]");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        Throwable cause = e.getTargetException();
        Helper_Log.add_info("Driver Listener : OnException : [" + cause.getMessage() + "]");

        if (target instanceof WebDriver) {
            WebDriver driver = (WebDriver) target;
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File fileScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
                File destinFile = new File("testResults/screenshots/error_screenshot.png");
                FileUtils.copyFile(fileScreenshot, destinFile);
                Helper_Log.add_info("Screenshot captured at: " + destinFile.getAbsolutePath());
            } catch (Exception ex) {
                Helper_Log.add_info("Failed to capture screenshot on error: " + ex.getMessage());
            }
        }
    }
}
