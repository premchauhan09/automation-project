package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class AddToCartSteps extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;

    @Given("user is logged in with username {string} and password {string}")
    public void user_is_logged_in(String username, String password) {
        setUp(); // launch browser
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        loginPage.login(username, password);
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Login failed, cannot test Add to Cart!");
    }

    @When("user adds a backpack to the cart")
    public void user_adds_backpack_to_cart() {
        productsPage.addBackpackToCart();
    }

    @Then("cart badge count should be {string}")
    public void cart_badge_count_should_be(String expectedCount) {
        String actualCount;
        try {
            actualCount = productsPage.getCartBadgeCount();
        } catch (Exception e) {
            actualCount = "0"; // no badge displayed
        }
        Assert.assertEquals(actualCount, expectedCount, "Cart badge mismatch!");
    }

    @When("user removes the backpack from the cart")
    public void user_removes_backpack_from_cart() {
        productsPage.removeBackpackFromCart();
    }
    
    @Then("close the browser")
    public void close_the_browser() {
        tearDown(); // this will quit the driver from BaseTest
    }
}
