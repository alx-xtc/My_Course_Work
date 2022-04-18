package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.Collections;

public class ProductsPage {
    protected WebDriver driver;
    private static final String ADD_TO_CARD_LOCATOR = "//button[@id='add-to-cart-sauce-labs-%s']";
    private static final String REMOVE_TO_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCardLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement burgerMenuBtn;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String productName) {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        String xpathElementToBeAdded = String.format(ADD_TO_CARD_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathElementToBeAdded));

        fluentWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    public boolean removeItemFromTheCart(String productName) {
        String xpathElementToBeAdded = String.format(REMOVE_TO_CART_LOCATOR, productName);
        WebElement removeButton = driver.findElement(By.xpath(xpathElementToBeAdded));

        if (removeButton.getText().equals("REMOVE")) {
            removeButton.click();
            return true;
        }else{
            return false;
        }
    }
    public int getItemsInTheCart(){
        if (shoppingCartCounter.getText().isEmpty()){
            return 0;
        } else {
            return Integer.parseInt(shoppingCartCounter.getText());
        }

    }
    public CartPage openCartPage(){

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        fluentWait.until(ExpectedConditions.elementToBeClickable(shoppingCardLink));
        shoppingCardLink.click();

        return new CartPage(driver);
    }
}

