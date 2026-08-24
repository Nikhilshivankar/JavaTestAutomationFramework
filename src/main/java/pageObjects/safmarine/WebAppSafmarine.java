package pageObjects.safmarine;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebAppSafmarine {

    WebDriver driver;
    @FindBy(xpath = "//*[@id='ss-nav-title-0' and contains (text(), 'Lookup')]")
    WebElement lookup;

    @FindBy(xpath = "//ul[@class='page-header__tabs__list']")
    List<WebElement> scheduleType;
    @FindBy(xpath = "//*[@id=\"searchSchedulesByPoint2Point\"]")
    WebElement pointToPoint;
    @FindBy(xpath = "//input[@name='originLocation']")
    WebElement fromCity;
    @FindBy(xpath = "//input[@name='destinationLocation']")
    WebElement toCity;
    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Search')]")
    WebElement btn_submit;

    public void launch_webApp() {

        String str_URL = "https://my.safmarine.com/schedules/";
        System.setProperty("webdriver.chrome.driver", "src\\main\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(str_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public WebElement waitForElementTobeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void clickWithJavaScriptExecutor(WebElement element) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", element);
    }
    public void select_ScheduleType(String str_schedule) {

        int size = scheduleType.size();
        System.out.println(size);
        for (WebElement e : scheduleType) {
            if (e.getText().equalsIgnoreCase(str_schedule) == true) {
                System.out.println(e.getText());
                e.click();
            }
        }
    }

    public void userAction() {

        waitForElementTobeClickable(pointToPoint);
        if (pointToPoint.isEnabled() == false) {
            pointToPoint.click();
        }

        waitForElementTobeClickable(fromCity);
        Actions act1 = new Actions(driver);
        act1.moveToElement(fromCity)
                .sendKeys("jawaharlal").pause(3000)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForElementTobeClickable(toCity);
        Actions act2 = new Actions(driver);
        act2.moveToElement(toCity)
                .click()
                .sendKeys("aqaba").pause(3000)
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER)
                .build()
                .perform();
        waitForElementTobeClickable(btn_submit);
        clickWithJavaScriptExecutor(btn_submit);
    }

    public static class MainRunner {

        public static void main(String[] args) {

            WebAppSafmarine safmarine = new WebAppSafmarine();
            safmarine.launch_webApp();
            safmarine.select_ScheduleType("vessel schedule");
            safmarine.select_ScheduleType("port call");
            safmarine.select_ScheduleType("pointtopoint");
            safmarine.userAction();

        }
    }
}
