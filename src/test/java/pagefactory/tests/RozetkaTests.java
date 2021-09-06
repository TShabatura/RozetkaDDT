package pagefactory.tests;

import model.RozetkaFilter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.XmlToObject;

import java.util.*;
import java.util.stream.Stream;

public class RozetkaTests extends BaseTest {
    @DataProvider(name = "rozetkaFilters", parallel = true)
    public Iterator<Object[]> rozetkaFilters(){
        List<RozetkaFilter> rozetkaFilters = new XmlToObject().inputData();
        return Stream.of(
                new Object[]{rozetkaFilters.get(0)},
                new Object[]{rozetkaFilters.get(1)},
                new Object[]{rozetkaFilters.get(2)},
                new Object[]{rozetkaFilters.get(3)},
                new Object[]{rozetkaFilters.get(4)}
        ).iterator();
    }
    @Test(dataProvider = "rozetkaFilters")
    public void checkTotalAmountOfCart(RozetkaFilter rozetkaFilter){
        getHomePage().inputSearchData(rozetkaFilter.getGroupOfThings());
        getProductCatalogPage().waitForPageLoadingComplete(60);
        getProductCatalogPage().searchByProducer(rozetkaFilter.getProducer());
        getProductCatalogPage().waitForFiltering(60, By.xpath("//div[@data-filter-name='producer']//label"));
        getProductCatalogPage().filterByProducer();
        getProductCatalogPage().waitForPageLoadingComplete(60);
        getProductCatalogPage().clickAddToCartButton();
        getProductCatalogPage().clickCartButton();
        getProductCatalogPage().waitForVisibilityOfElement(60, getProductCatalogPage().getPopup());
        Assert.assertTrue(getCartPopup().getTotalAmountOfCart() < rozetkaFilter.getTotalAmount());
    }
}
