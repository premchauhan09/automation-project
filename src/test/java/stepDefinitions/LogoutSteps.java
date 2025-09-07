package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import pages.HomePage;
import utils.BaseTest;

public class LogoutSteps extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @Given("user has logged in with username {string} and password {string}")
    public void user_has_logged_in(String username, String password) {
        // Manually call setup from BaseTest
        setUp();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.login(username, password);
        Assert.assertTrue(homePage.isProductsPageDisplayed(), "Login failed, cannot test logout!");
    }

    @When("user clicks on logout")
    public void user_clicks_logout() {
        homePage.logout();
    }

    @Then("user should be redirected back to login page")
    public void verify_logout() {
        Assert.assertTrue(homePage.isLoginPageDisplayed(), "Logout failed, still not on login page!");
        tearDown(); 
    }
}
