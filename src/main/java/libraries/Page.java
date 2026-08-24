package libraries;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected static WebDriver driver;
    
    protected Page(WebDriver driver){
        this.driver = driver;
    }
    
    public abstract void checkLoadCondition();
        
    public abstract void checkExitCondition();
    
    public void disposeDriver() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
    
    public WebElement waitForElementTobeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickWithJavaScriptExecutor(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", element);
    }

    public void userAction1(WebElement element, String strValue) {
        waitForElementTobeClickable(element);
        Actions act = new Actions(driver);
        act.moveToElement(element)
                .click()
                .sendKeys(strValue).pause(Duration.ofSeconds(2))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        
        System.out.println(element.getText());
    }
    
    public WebElement setRadioButton(List<WebElement> list, String strRefValue) {
        for (WebElement element : list) {
            System.out.println("Checking element with value " + element.getAttribute("value") + "/" + strRefValue);
            if (element.getAttribute("value").equals(strRefValue)) {
                System.out.println("Selecting element with value " + element.getAttribute("value") + "/" + strRefValue);
                clickWithJavaScriptExecutor(element);
                return element;
            }
        }
        return null;
    }
    
    public void set_dropdown(List<WebElement> list, String strValue) {
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(strValue)){
                element.sendKeys(strValue);
                element.click();
            }
        }
    }

    public void set_dropdown(WebElement element, String strValue) {
        element.click();
        Select select = new Select(element);
        select.selectByVisibleText(strValue);
    }
}
