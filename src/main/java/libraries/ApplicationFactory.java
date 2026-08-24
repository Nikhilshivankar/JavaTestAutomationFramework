package libraries;

import org.openqa.selenium.WebDriver;
import pageObjects.clearTrip.PO_CT_HomePage;
import pageObjects.safmarine.*;
import pageObjects.sauceDemo.PO_LoginPage;
import pageObjects.sauceDemo.PO_InventoryPage;

public class ApplicationFactory {

    public Safmarine safmarine;
    public ClearTrip clearTrip;
    public SauceDemo sauceDemo;

    public class Application {
        protected WebDriver driver;
        public Browser browser = new Browser();

        public WebDriver launchWebApp(String strUrl) {
            driver = browser.getBrowserDriver("chrome");
            driver.navigate().to(strUrl);
            return driver;
        }

        public void closeWebApp() {
            browser.quitBrowser();
        }

        public WebDriver getDriver() {
            return this.driver;
        }
    }

    public Safmarine getNewInstance_Safmarine(String strUrl) {
        return (safmarine == null) ? safmarine = new Safmarine(strUrl) : safmarine;
    }

    public ClearTrip getNewInstance_ClearTrip(String strUrl) {
        return (clearTrip == null) ? clearTrip = new ClearTrip(strUrl) : clearTrip;
    }

    public SauceDemo getNewInstance_SauceDemo(String strUrl) {
        return (sauceDemo == null) ? sauceDemo = new SauceDemo(strUrl) : sauceDemo;
    }

    public class ClearTrip extends Application {
        private PO_CT_HomePage po_CT_HomePage;

        public ClearTrip(String strUrl) {
            this.driver = launchWebApp(strUrl);
        }

        public PO_CT_HomePage get_HomePage() {
            return (po_CT_HomePage == null) ? po_CT_HomePage = new PO_CT_HomePage(driver) : po_CT_HomePage;
        }
    }

    public class Safmarine extends Application {
        private PO_HomePage po_homepage;
        private PO_LookUp_PortCall po_LookUp_PortCall;
        private PO_LookUp_P2P po_lookUp_P2P;
        private PO_LookUp_Vessal po_LookUp_Vessal;

        public Safmarine(String strUrl) {
            this.driver = launchWebApp(strUrl);
        }

        public PO_HomePage getHomePage() {
            return (po_homepage == null) ? po_homepage = new PO_HomePage(driver) : po_homepage;
        }

        public PO_LookUp_PortCall getPortCallPage() {
            return (po_LookUp_PortCall == null) ? po_LookUp_PortCall = new PO_LookUp_PortCall(driver) : po_LookUp_PortCall;
        }

        public PO_LookUp_P2P getPoint2PointPage() {
            return (po_lookUp_P2P == null) ? po_lookUp_P2P = new PO_LookUp_P2P(driver) : po_lookUp_P2P;
        }

        public PO_LookUp_Vessal getVessalSchedulepage() {
            return (po_LookUp_Vessal == null) ? po_LookUp_Vessal = new PO_LookUp_Vessal(driver) : po_LookUp_Vessal;
        }
    }

    public class SauceDemo extends Application {
        private PO_LoginPage po_loginPage;
        private PO_InventoryPage po_inventoryPage;

        public SauceDemo(String strUrl) {
            this.driver = launchWebApp(strUrl);
        }

        public PO_LoginPage getLoginPage() {
            return (po_loginPage == null) ? po_loginPage = new PO_LoginPage(driver) : po_loginPage;
        }

        public PO_InventoryPage getInventoryPage() {
            return (po_inventoryPage == null) ? po_inventoryPage = new PO_InventoryPage(driver) : po_inventoryPage;
        }
    }
}
