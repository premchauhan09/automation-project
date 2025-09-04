package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

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

    // Open cart
    public void openCart() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        highlightElement(element);
        element.click();
    }

    // Get number of items in cart
    public int getCartItemsCount() {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement e : items) {
            highlightElement(e);
        }
        return items.size();
    }

    // Check if a product is present in cart
    public boolean isProductInCart(String productName) {
        List<WebElement> items = driver.findElements(cartItemNames);
        for (WebElement item : items) {
            highlightElement(item);
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Remove Backpack (wait until gone)
    public void removeBackpackFromCart() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(removeBackpack));
        highlightElement(element);
        element.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(cartItems, 0));
    }

    // ✅ Checkout flow
    public void clickCheckout() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        highlightElement(element);
        element.click();
    }

    public void fillCheckoutDetails(String firstName, String lastName, String postalCode) {
        WebElement first = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        highlightElement(first);
        first.sendKeys(firstName);

        WebElement last = driver.findElement(lastNameField);
        highlightElement(last);
        last.sendKeys(lastName);

        WebElement postal = driver.findElement(postalCodeField);
        highlightElement(postal);
        postal.sendKeys(postalCode);

        WebElement cont = driver.findElement(continueButton);
        highlightElement(cont);
        cont.click();
    }

    public void finishCheckout() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        highlightElement(element);
        element.click();
    }

    public boolean isOrderComplete() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteMsg));
        highlightElement(element);
        return element.isDisplayed();
    }
}
