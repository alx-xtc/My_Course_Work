package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;
import java.io.IOException;

public class BuyProductTest extends TestUtil {

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/checkout.csv");
    }

    @Test(dataProvider = "csvUserList")
    public void BuyProductTest(String userName, String password, String product1, String product2,
                               String firstName, String lastName, String postalCode){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(product1);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "Because we have only one item");
        productsPage.addItemToTheCart(product2);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have two items");

        CartPage cartPage = productsPage.openCartPage();

        CheckoutYourInfoPage checkoutYourInfoPage = cartPage.openCheckoutPage();
        checkoutYourInfoPage.fillInfo(firstName, lastName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInfoPage.openCheckoutOverviewPage();

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.openCheckoutCompletePage();
        Assert.assertTrue(checkoutCompletePage.thankYouMessageDisplayed(), "THANK YOU FOR YOUR ORDER");
    }
}
