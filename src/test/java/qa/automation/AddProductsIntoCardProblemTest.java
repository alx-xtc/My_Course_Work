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

public class AddProductsIntoCardProblemTest extends TestUtil {
    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/AddProductsIntoCardProblemTest.csv");
    }

    @Test(dataProvider = "csvUserList")
    public void addItemToTheCart(String userName, String password, String product1,
                                 String product2,String product3){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(product1);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "Because we have only one item");

        productsPage.addItemToTheCart(product2);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have two items");

        productsPage.addItemToTheCart(product3);
        Assert.assertEquals(productsPage.getItemsInTheCart(), 3, "Because we have three items");
    }
}

