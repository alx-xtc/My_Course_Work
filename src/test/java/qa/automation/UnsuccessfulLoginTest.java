package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvHelper;
import java.io.IOException;

public class UnsuccessfulLoginTest extends TestUtil {

    @DataProvider(name = "csvUserList")
    public static Object[][] readFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/unsuccessfulLogins.csv");
    }

    @Test(dataProvider = "csvUserList")

    public void UnsuccessfulLogin(String userName, String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.tryToLogin(userName, password);

        Assert.assertTrue(loginPage.isLoginErrorMessageShown());
    }

}
