package ui_TESTING;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import libraries.ApplicationFactory;
import libraries.PropertyReader;

public class UI_Test {

    private ApplicationFactory appFactory;
    private ApplicationFactory.SauceDemo sauceDemo;
    private PropertyReader propReader;

    @BeforeMethod
    public void setup() {
        appFactory = new ApplicationFactory();
        propReader = new PropertyReader();
        String url = "https://www.saucedemo.com";
        try {
            url = propReader.getProperty("testUrl_sauce");
        } catch (Exception e) {
            System.out.println("Could not read 'testUrl_sauce' from config. Using default.");
        }
        sauceDemo = appFactory.getNewInstance_SauceDemo(url);
    }

    @Test
    public void testSauceDemoCartFlow() {
        // Log in to application
        sauceDemo.getLoginPage().login("standard_user", "secret_sauce");

        // Verify landing page title
        String title = sauceDemo.getInventoryPage().getPageTitleText();
        Assert.assertEquals(title, "Products", "Verify catalog page title matches 'Products'");

        // Add item to cart
        sauceDemo.getInventoryPage().addBackpackToCart();

        // Verify cart badge count is updated
        String cartCount = sauceDemo.getInventoryPage().getCartItemsCount();
        Assert.assertEquals(cartCount, "1", "Verify cart icon badge displays '1' after addition");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (sauceDemo != null) {
            sauceDemo.closeWebApp();
        }
    }
}
