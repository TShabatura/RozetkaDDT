package pagefactory.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pagefactory.CartPopup;
import pagefactory.HomePage;
import pagefactory.ProductCatalogPage;
import util.DriverManager;
import util.PropertiesReader;

public class BaseTest {
    private WebDriver driver;
    @BeforeTest
    public void setUp(){
        PropertiesReader propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getDriverName(), propertiesReader.getDriverLocation());
    }

    @BeforeMethod
    public void testSetUp(){
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.closeDriver();
    }


    public CartPopup getCartPopup(){
        return new CartPopup(DriverManager.getDriver());
    }

    public HomePage getHomePage(){
        return new HomePage(DriverManager.getDriver());
    }

    public ProductCatalogPage getProductCatalogPage(){
        return new ProductCatalogPage(DriverManager.getDriver());
    }
}
