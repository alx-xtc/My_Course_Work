package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    protected WebDriver driver;

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean thankYouMessageDisplayed(){
        WebElement thankYouMessage = driver.findElement(By.xpath("//*[text()='THANK YOU FOR YOUR ORDER']"));
        return thankYouMessage.isDisplayed();
    }
}
