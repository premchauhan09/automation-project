package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import base.BaseTest;
import page.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce", true, ""},
            {"locked_out_user", "secret_sauce", false, "Error: Sorry, this user has been locked out."},
            {"problem_user", "secret_sauce", true, ""},
            {"performance_glitch_user", "secret_sauce", true, ""},
            {"error_user", "secret_sauce", true, ""},
            {"visual_user", "secret_sauce", true, ""},
            {"standard_user", "wrong_pass", false, "Error: Username and password do not match any user in this service"},
            {"invalid_user", "secret_sauce", false, "Error: Username and password do not match any user in this service"},
            {"", "secret_sauce", false, "Error: Username is required"},
            {"standard_user", "", false, "Error: Password is required"},
            {"", "", false, "Error: Username is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean isSuccess, String expectedError) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.login(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if (isSuccess) {
            // Wait for Products page to be visible
            wait.until(ExpectedConditions.visibilityOf(loginPage.getProductsTitleElement()));
            Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login should succeed but failed!");
        } else {
            // Wait for error message to be visible
            wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));
            Assert.assertEquals(loginPage.getErrorMessage().trim(), expectedError, "Error message mismatch!");
        }

        Thread.sleep(2000);
    }
}