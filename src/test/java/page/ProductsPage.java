package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartBadge = By.className("shopping_cart_badge");
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Utility: highlight element with red border
    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
        try {
            Thread.sleep(200); // small pause to see the highlight
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', '');", element); // remove highlight
    }

    // Add Backpack to cart
    public void addBackpackToCart() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack));
        highlightElement(element);
        element.click();
        // wait until cart badge updates
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
    }

    // Remove Backpack from cart
    public void removeBackpackFromCart() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(removeBackpack));
        highlightElement(element);
        element.click();
        // wait until cart badge disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
    }

    // Get cart badge count
    public String getCartBadgeCount() {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        highlightElement(badge);
        return badge.getText();
    }

    // Navigate to Cart Page
    public void openCart() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        highlightElement(element);
        element.click();
    }
}
