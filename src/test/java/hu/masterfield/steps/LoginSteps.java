package hu.masterfield.steps;

import hu.masterfield.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import hu.masterfield.pages.LoginPage;
import io.cucumber.java.PendingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;


public class LoginSteps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public static void setup(){
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public static void cleanup(){
        if(driver!=null){
            driver.quit();
        }
    }

    @Given("the user opens the DigitalBank webpage")
    public void openDigitalBankWebpage(){
        BasePage basePage = new BasePage(driver, wait);
        driver.get(basePage.getBaseUrl());
    }

    @And("accepts cookies")
    public void acceptCookies(){
        BannerPage bannerPage = new BannerPage(driver, wait);
        bannerPage.checkIfCookieBannerIsPresent();
        bannerPage.acceptCookies();
        bannerPage.checkLoginPageAfterCookies();
    }

    @When("logs in with valid username and password using datatable")
    public void loginWithValidCredentials(DataTable table){
       LoginPage loginPage = new LoginPage(driver, wait);

       Map<String, String> loginData = table.asMap(String.class, String.class);

       String username = loginData.get("username");
       String password = loginData.get("password");

       loginPage.login(username, password);
    }

    @Then("the Home page is displayed")
    public void theHomePageIsDisplayed() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.checkIfUserIsLoggedIn();
    }

}
