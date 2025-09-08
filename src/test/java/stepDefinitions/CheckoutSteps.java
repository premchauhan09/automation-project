package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.CartPage;
import utils.BaseTest;

public class CheckoutSteps extends BaseTest {

    private HomePage homePage;
    private CartPage cartPage;

    @Given("I am logged in as a standard user for checkout")
    public void i_am_logged_in_as_standard_user_for_checkout() {
        setUp(); // from BaseTest
        loginAsStandardUser();
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        Assert.assertTrue(homePage.isProductsPageDisplayed(), "Products page should be displayed");
    }

    @When("I add {string} to the cart")
    public void i_add_item_to_the_cart(String itemName) {
        homePage.addItemToCart(itemName);
    }

    @When("I go to the cart")
    public void i_go_to_the_cart() {
        cartPage.openCart();
    }

    @When("I proceed to checkout with details {string}, {string}, {string}")
    public void i_proceed_to_checkout_with_details(String firstName, String lastName, String zipCode) {
        cartPage.clickCheckout();
        cartPage.fillCheckoutDetails(firstName, lastName, zipCode);
        cartPage.finishCheckout();
    }

    @Then("the order should be completed successfully")
    public void the_order_should_be_completed_successfully() {
        Assert.assertTrue(cartPage.isOrderComplete(), "Order should be completed successfully");
        tearDown(); // close browser
    }
}
