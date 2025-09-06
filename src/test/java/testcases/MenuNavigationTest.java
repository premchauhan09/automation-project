package testcases;

import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class MenuNavigationTest extends BaseTest {

    @Test
    public void verifyMenuButtonClickable() {
        // ✅ First login
        loginAsStandardUser();

        // Now we are on the Products page
        HomePage homePage = new HomePage(driver);

        // Click the menu button
        homePage.clickMenuButton();

        // Verify menu is visible
        Assert.assertTrue(homePage.isMenuVisible(), "Menu should be visible after clicking the button");
    }
}
