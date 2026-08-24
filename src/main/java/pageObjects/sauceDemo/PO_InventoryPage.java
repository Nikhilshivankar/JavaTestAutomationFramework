package pageObjects.sauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import libraries.Page;

public class PO_InventoryPage extends Page {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public PO_InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void checkLoadCondition() {
        waitForElementTobeClickable(addBackpackButton);
    }

    @Override
    public void checkExitCondition() {
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public void addBackpackToCart() {
        addBackpackButton.click();
    }

    public String getCartItemsCount() {
        try {
            return shoppingCartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCart() {
        shoppingCartLink.click();
    }
}
