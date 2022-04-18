package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.Collections;

public class CheckoutOverviewPage {

    protected WebDriver driver;
    private static final String FINISH_LOCATOR = "//button[@id='finish']";

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutCompletePage openCheckoutCompletePage(){

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoClassDefFoundError.class));

        WebElement continueShoppingLink = driver.findElement(By.xpath(FINISH_LOCATOR));

        fluentWait.until(ExpectedConditions.elementToBeClickable(continueShoppingLink));
        continueShoppingLink.click();

        return new CheckoutCompletePage(driver);
    }
}
