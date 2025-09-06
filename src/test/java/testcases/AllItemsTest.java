package testcases;

import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class AllItemsTest extends BaseTest {

    @Test
    public void verifyAllItemsClickable() {
        loginAsStandardUser(); // 👈 must login first
        HomePage homePage = new HomePage(driver);
        homePage.clickMenuButton();
        homePage.clickAllItems();
        Assert.assertTrue(homePage.isProductListVisible(),
                "Product list should be visible after clicking All Items");
    }
}
