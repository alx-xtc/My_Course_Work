package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;
import java.io.IOException;

public class RemoveProductsProblemTest extends TestUtil {

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/problemUserRemoveProducts.csv");
    }

    @Test(dataProvider = "csvUserList")

    public void addItemToTheCart(String userName, String password, String product1, String product2,
                                 String product3){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(product1);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "We have only one item");

        productsPage.addItemToTheCart(product2);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "We have two items");

        productsPage.addItemToTheCart(product3);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 3, "We have three items");

        productsPage.removeItemFromTheCart(product1);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "We remove product1");

        productsPage.removeItemFromTheCart(product2);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "We remove product2");

        productsPage.removeItemFromTheCart(product3);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 0, "We remove product3");

    }
}
