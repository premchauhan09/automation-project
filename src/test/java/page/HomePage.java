package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By productsTitle = By.className("title");
    private By loginButton = By.id("login-button"); // appears on login page

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ Check if Products page loaded
    public boolean isProductsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle)).isDisplayed();
    }

    // ✅ Perform Logout safely
    public void logout() {
        // open menu first
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        
        // ensure logout link is visible before clicking
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        
        // wait until redirected back to login page
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    // ✅ Verify back on Login page
    public boolean isLoginPageDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }
}
