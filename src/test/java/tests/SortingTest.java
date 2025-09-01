package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;

public class SortingTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void setupTest() {
        // First login
        loginAsStandardUser();
        // Land on Products page
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProductsPageDisplayed(), "Products page not displayed after login!");
    }

    @Test
    public void verifySortByNameAscending() {
        homePage.sortProducts("az"); // A → Z
        Assert.assertTrue(homePage.isSortedByNameAscending(), "Products not sorted A-Z");
    }

    @Test
    public void verifySortByNameDescending() {
        homePage.sortProducts("za"); // Z → A
        Assert.assertTrue(homePage.isSortedByNameDescending(), "Products not sorted Z-A");
    }

    @Test
    public void verifySortByPriceLowToHigh() {
        homePage.sortProducts("lohi"); // Low → High
        Assert.assertTrue(homePage.isSortedByPriceLowToHigh(), "Products not sorted Low-High");
    }

    @Test
    public void verifySortByPriceHighToLow() {
        homePage.sortProducts("hilo"); // High → Low
        Assert.assertTrue(homePage.isSortedByPriceHighToLow(), "Products not sorted High-Low");
    }
}
