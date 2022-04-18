package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.Collections;

public class CartPage {
    protected WebDriver driver;
    private static final String CHECKOUT_LOCATOR = "//*[@id='checkout']";

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutYourInfoPage openCheckoutPage(){
        WebElement checkoutLink = driver.findElement(By.xpath(CHECKOUT_LOCATOR));
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        fluentWait.until(ExpectedConditions.elementToBeClickable(checkoutLink));
        checkoutLink.click();

        return new CheckoutYourInfoPage(driver);
    }
}
