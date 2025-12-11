package hu.masterfield.steps;

import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.ProfilePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class UpdateProfileSteps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @And("logs in with valid credentials")
    public void logsInWithValidCredentials(DataTable table) {
        LoginPage loginPage = new LoginPage(driver, wait);

        Map<String, String> loginData = table.asMap(String.class, String.class);

        String username = loginData.get("username");
        String password = loginData.get("password");

        loginPage.login(username, password);
    }

    @And("is on the MyProfile page")
    public void navigateToMyProfilePage(){
        HomePage homePage = new HomePage(driver, wait);
        homePage.openMyProfile();
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.checkProfilePage();
    }
}
