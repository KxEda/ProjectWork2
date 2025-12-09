package hu.masterfield.steps;

import hu.masterfield.BaseTest;
import hu.masterfield.driver.Settings;
import hu.masterfield.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private ChromeDriver driver;
    private WebDriverWait wait;
    private static final String HOME_MENU_XPATH="//a[@id=\"home-menu-item\"]";

    @Given("the user opens the DigitalBank webpage")
    public void openDigitalBankWebpage(){
        Settings.getBaseUrl();
    }

    @And("logs in with valid username and password using datatable")
    public void loginWithValidCredentials(String username, String password){
        this.driver = (ChromeDriver) BaseTest.getDriver();
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);
        }


    @Then("the Home page is displayed")
    public void verifyHeaderTexts(List<Map<String, String>> table){
        WebElement homeMenu = driver.findElement(By.xpath(HOME_MENU_XPATH));
       assertTrue(homeMenu.getText().equals("Home"));
    }
}
