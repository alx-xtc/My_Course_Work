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

public class CheckoutYourInfoPage {

    protected WebDriver driver;
    private static final String CONTINUE_LOCATOR = "//*[@id='continue']";

    @FindBy(css = "[id=first-name]")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueBtn;

    public CheckoutYourInfoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInfo(String firstName, String lastName, String postalCode){

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        fluentWait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        fluentWait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        lastNameInput.click();
        lastNameInput.sendKeys(lastName);

        fluentWait.until(ExpectedConditions.elementToBeClickable(postalCodeInput));
        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);

        if (lastNameInput.getText().isEmpty()){
                System.out.println("Error: Last Name is required");
        }

    }

    public CheckoutOverviewPage openCheckoutOverviewPage(){

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement continueShoppingLink = driver.findElement(By.xpath(CONTINUE_LOCATOR));

        fluentWait.until(ExpectedConditions.elementToBeClickable(continueShoppingLink));
        continueShoppingLink.click();

        return new CheckoutOverviewPage(driver);
    }
}
