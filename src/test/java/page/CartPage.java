package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    // Cart item locators
    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");

    // Checkout locators
    private By cartIcon = By.className("shopping_cart_link");
    private By checkoutButton = By.id("checkout");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By orderCompleteMsg = By.className("complete-header");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ Open cart
    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    // ✅ Get number of items in cart
    public int getCartItemsCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    // ✅ Check if a product is present in cart
    public boolean isProductInCart(String productName) {
        List<WebElement> items = driver.findElements(cartItemNames);
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // ✅ Remove Backpack (wait until gone)
    public void removeBackpackFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBackpack)).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(cartItems, 0));
    }

    // ✅ Checkout flow
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void fillCheckoutDetails(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isOrderComplete() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteMsg)).isDisplayed();
    }
}
