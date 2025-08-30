package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.LoginPage;
import page.ProductsPage;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddToCart() {
        // Login first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add product to cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();

        // Verify cart badge count
        String count = productsPage.getCartBadgeCount();
        Assert.assertEquals(count, "1", "Cart count should be 1 after adding product");

        // Remove product
        productsPage.removeBackpackFromCart();

        // Verify cart badge disappears
        try {
            count = productsPage.getCartBadgeCount();
        } catch (Exception e) {
            count = "0"; // no badge displayed
        }
        Assert.assertEquals(count, "0", "Cart should be empty after removing product");
    }
}
