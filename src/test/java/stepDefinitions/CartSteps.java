package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import utils.BaseTest;

public class CartSteps extends BaseTest {

    private ProductsPage productsPage;
    private CartPage cartPage;

    @Given("I am logged in as a standard user for cart")
    public void i_am_logged_in_as_a_standard_user() {
        setUp();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @When("I add the Sauce Labs Backpack to the cart")
    public void i_add_the_sauce_labs_backpack_to_the_cart() {
        productsPage.addBackpackToCart();
    }

    @Then("the product should be visible in the cart")
    public void the_product_should_be_visible_in_the_cart() {
        productsPage.openCart();
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
    }

    @When("I remove the Sauce Labs Backpack from the cart")
    public void i_remove_the_sauce_labs_backpack_from_the_cart() {
        cartPage.removeBackpackFromCart();
    }

    @Then("the product should not be visible in the cart")
    public void the_product_should_not_be_visible_in_the_cart() {
        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
        tearDown();
    }
}
