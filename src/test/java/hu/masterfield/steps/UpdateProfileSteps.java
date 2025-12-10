package hu.masterfield.steps;

import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.ProfilePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateProfileSteps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @And("is on the MyProfile page")
    public void navigateToMyProfilePage(){
        HomePage homePage = new HomePage(driver, wait);
        homePage.openMyProfile();
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.checkProfilePage();
    }
}
