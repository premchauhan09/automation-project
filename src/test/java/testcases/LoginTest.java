package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import utils.LoginBaseTest;
import pages.LoginPage;

public class LoginTest extends LoginBaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getProductsTitleElement()));

        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login should succeed but failed!");
    }

    @Test
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        Assert.assertEquals(
            loginPage.getErrorMessage().trim(),
            "Epic sadface: Sorry, this user has been locked out.",
            "Error message mismatch!"
        );
    }

    @Test
    public void testProblemUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getProductsTitleElement()));

        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login should succeed but failed!");
    }

    @Test
    public void testPerformanceGlitchUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("performance_glitch_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getProductsTitleElement()));

        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login should succeed but failed!");
    }

    @Test
    public void testErrorUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("error_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getProductsTitleElement()));

        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login should succeed but failed!");
    }

    @Test
    public void testVisualUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("visual_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getProductsTitleElement()));

        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Login should succeed but failed!");
    }

    @Test
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_pass");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        Assert.assertEquals(
            loginPage.getErrorMessage().trim(),
            "Epic sadface: Username and password do not match any user in this service",
            "Error message mismatch!"
        );
    }

    @Test
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        Assert.assertEquals(
            loginPage.getErrorMessage().trim(),
            "Epic sadface: Username and password do not match any user in this service",
            "Error message mismatch!"
        );
    }

    @Test
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        Assert.assertEquals(
            loginPage.getErrorMessage().trim(),
            "Epic sadface: Username is required",
            "Error message mismatch!"
        );
    }

    @Test
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        Assert.assertEquals(
            loginPage.getErrorMessage().trim(),
            "Epic sadface: Password is required",
            "Error message mismatch!"
        );
    }

    @Test
    public void testEmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageElement()));

        Assert.assertEquals(
            loginPage.getErrorMessage().trim(),
            "Epic sadface: Username is required",
            "Error message mismatch!"
        );
    }
}
