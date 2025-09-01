package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.CartPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutFlow() {
        // 🔑 Login first
        loginAsStandardUser();

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        // Add item to cart
        homePage.addItemToCart("Sauce Labs Bike Light");

        // Go to cart
        cartPage.openCart();

        // Perform checkout steps
        cartPage.clickCheckout();
        cartPage.fillCheckoutDetails("John", "Doe", "12345");
        cartPage.finishCheckout();

        // Verify order completion
        Assert.assertTrue(cartPage.isOrderComplete(), "Order should be completed successfully");
    }
}
