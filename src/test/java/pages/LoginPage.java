package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.Collections;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(css = "[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginBtn;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement burgerMenuBtn;

    @FindBy(xpath = "//*[text()='Epic sadface: Username and password do not match any user in this service']")
    private WebElement genericErrorMessage;

    @FindBy(xpath = "//*[text()='Epic sadface: Username is required']")
    private WebElement userNameEmptyErrorMessage;

    @FindBy(xpath = "//*[text()='Epic sadface: Sorry, this user has been locked out.']")
    private WebElement useHasBeenLockedOut;

    @FindBy(xpath = "//*[text()='Epic sadface: Password is required']")
    private WebElement passwordEmptyErrorMessage;

    @FindBy(xpath = "//button[@class='error-button']")
    private WebElement xButtonOfLoginErrorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login(String username, String password) {

        FluentWait fluentWait = new FluentWait(driver)
        .withTimeout(Duration.ofSeconds(20))
        .pollingEvery(Duration.ofSeconds(5))
        .ignoreAll(Collections.singleton(NoSuchElementException.class));

        fluentWait.until(ExpectedConditions.elementToBeClickable(userNameInput));
        userNameInput.click();
        userNameInput.sendKeys(username);

        fluentWait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordInput.click();
        passwordInput.sendKeys(password);

        fluentWait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();

        return new ProductsPage(driver);
    }

    public void tryToLogin (String username, String password) {

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        fluentWait.until(ExpectedConditions.elementToBeClickable(userNameInput));
        userNameInput.click();
        userNameInput.sendKeys(username);

        fluentWait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordInput.click();
        passwordInput.sendKeys(password);

        fluentWait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
   }

    public boolean isLoginErrorMessageShown(){

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        fluentWait.until(ExpectedConditions.elementToBeClickable(xButtonOfLoginErrorMessage));
        return xButtonOfLoginErrorMessage.isDisplayed();
    }
}
