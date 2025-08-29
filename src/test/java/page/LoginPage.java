package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton   = By.id("login-button");
    private By errorMessage  = By.cssSelector("h3[data-test='error']");
    private By productsTitle = By.className("title");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // max wait 5s
    }

    // --- Action Methods ---
    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear(); // clear in case something is pre-filled
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // --- Getter Methods for Assertions ---
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isProductsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle)).isDisplayed();
    }

    // Expose elements (for test class explicit waits)
    public WebElement getProductsTitleElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle));
    }

    public WebElement getErrorMessageElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }
}
