package testcases;

import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class MenuOptionsTest extends BaseTest {

    @Test
    public void verifyAboutLink() {
        // ✅ Login first
        loginAsStandardUser();

        HomePage homePage = new HomePage(driver);
        homePage.clickMenuButton();
        homePage.clickAbout();

        Assert.assertTrue(
                homePage.isAboutPageDisplayed(),
                "About page should be displayed"
        );
    }

    @Test
    public void verifyResetAppState() {
        // ✅ Login first
        loginAsStandardUser();

        HomePage homePage = new HomePage(driver);
        homePage.addItemToCart("Sauce Labs Backpack");
        homePage.clickMenuButton();
        homePage.clickResetAppState();

        Assert.assertEquals(
                homePage.getCartCount(),
                0,
                "Cart should be empty after Reset App State"
        );
    }
}
