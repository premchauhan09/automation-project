package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.LoginPage;
import page.HomePage;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.isProductsPageDisplayed(), "Login failed, cannot test logout!");

        // Step 2: Logout
        homePage.logout();

        // Step 3: Verify we are back on Login page
        Assert.assertTrue(homePage.isLoginPageDisplayed(), "Logout failed, still not on login page!");
    }
}
