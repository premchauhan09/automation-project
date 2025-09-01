package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By productsTitle = By.className("title");
    private By loginButton = By.id("login-button"); // appears on login page

    // Menu options
    private By menuCloseButton = By.id("react-burger-cross-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");

    // Cart locators
    private By cartBadge = By.className("shopping_cart_badge");
    private By inventoryContainer = By.id("inventory_container");

    // Sorting locators (✅ fixed)
    private By sortDropdown = By.cssSelector("select[data-test='product-sort-container']");
    private By itemNames = By.className("inventory_item_name");
    private By itemPrices = By.className("inventory_item_price");

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
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    // ✅ Verify back on Login page
    public boolean isLoginPageDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    // -------------------------------
    // 🔹 Menu actions
    // -------------------------------
    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public boolean isMenuVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(menuCloseButton)).isDisplayed();
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(allItemsLink)).click();
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink)).click();
    }

    public void clickResetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(resetAppStateLink)).click();
    }

    // -------------------------------
    // 🔹 Cart Actions
    // -------------------------------
    public void addItemToCart(String productName) {
        By addToCartBtn = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isProductListVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }

    public boolean isAboutPageDisplayed() {
        return driver.getCurrentUrl().contains("saucelabs.com");
    }

    // -------------------------------
    // 🔹 Sorting Actions
    // -------------------------------
    public void sortProducts(String sortValue) {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(sortDropdown));
        new Select(dropdown).selectByValue(sortValue);

        // Wait for list to refresh
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames));

        // 👇 Optional: small delay for visual stability
        try {
            Thread.sleep(1000); // 1 second pause (adjust if needed)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public boolean isSortedByNameAscending() {
        List<String> names = getItemNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(String::compareToIgnoreCase);
        return names.equals(sorted);
    }

    public boolean isSortedByNameDescending() {
        List<String> names = getItemNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        return names.equals(sorted);
    }

    public boolean isSortedByPriceLowToHigh() {
        List<Double> prices = getItemPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        return prices.equals(sorted);
    }

    public boolean isSortedByPriceHighToLow() {
        List<Double> prices = getItemPrices();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        return prices.equals(sorted);
    }

    // -------------------------------
    // 🔹 Helper methods
    // -------------------------------
    private List<String> getItemNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames));
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText().trim());
        }
        return names;
    }

    private List<Double> getItemPrices() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemPrices));
        List<Double> prices = new ArrayList<>();
        for (WebElement e : elements) {
            prices.add(Double.parseDouble(e.getText().replace("$", "").trim()));
        }
        return prices;
    }
}
