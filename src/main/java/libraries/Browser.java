package libraries;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import listeners.Listen_WebDriver;

public class Browser {

    private WebDriver driver;
    public WebDriver decoratedDriver;

    public WebDriver getBrowserDriver(String str_BrowserName) {
        WebDriver rawDriver = null;

        if (str_BrowserName.toLowerCase().contains("internet") || str_BrowserName.toLowerCase().contains("ie")) {
            rawDriver = new InternetExplorerDriver();
        } else if (str_BrowserName.toLowerCase().contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "d:\\Learning_TestAutomation\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            rawDriver = new ChromeDriver(options);
            rawDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("In Browser Class: Created Chrome Driver instance via manual path");
        } else if (str_BrowserName.toLowerCase().contains("firefox")) {
            rawDriver = new FirefoxDriver();
        } else {
            System.out.println("No valid browser name provided: " + str_BrowserName + ". Defaulting to Chrome.");
            System.setProperty("webdriver.chrome.driver", "d:\\Learning_TestAutomation\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            rawDriver = new ChromeDriver(options);
        }

        rawDriver.manage().window().maximize();

        // Setup WebDriver Listener using Selenium 4's EventFiringDecorator
        WebDriverListener listener = new Listen_WebDriver();
        decoratedDriver = new EventFiringDecorator<>(listener).decorate(rawDriver);
        driver = decoratedDriver;

        return driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void closeActiveDriverSession() {
        if (driver != null) {
            driver.close();
        }
    }

    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
