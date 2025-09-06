package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import utils.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;

public class CartTest extends BaseTest {

	@Test
	public void testAddAndRemoveFromCart() throws InterruptedException {
	    // Login
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login("standard_user", "secret_sauce");

	    // Add product
	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addBackpackToCart();

	    // Pause so you can see the item added
	    TimeUnit.SECONDS.sleep(2);

	    productsPage.openCart();

	    CartPage cartPage = new CartPage(driver);

	    // Verify added
	    Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
	    
	    TimeUnit.SECONDS.sleep(2);

	    // Remove product
	    cartPage.removeBackpackFromCart();

	    TimeUnit.SECONDS.sleep(2);

	    // Verify removed
	    Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
	}

}
