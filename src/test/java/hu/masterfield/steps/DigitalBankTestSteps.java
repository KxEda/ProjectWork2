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
import java.util.Map;


public class DigitalBankTestSteps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public static void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user opens the DigitalBank webpage")
    public void openDigitalBankWebpage() {
        BasePage basePage = new BasePage(driver, wait);
        driver.get(basePage.getBaseUrl());
        basePage.pageIsLoaded(basePage.getDigitalBankLogo());
    }

    @And("accepts cookies")
    public void acceptCookies() {
        BannerPage bannerPage = new BannerPage(driver, wait);
        bannerPage.pageIsLoaded(bannerPage.getCookieBanner());
        bannerPage.acceptCookies();
        bannerPage.checkIfCookiesBannerStillVisible();
        bannerPage.pageIsLoaded(bannerPage.getDigitalBankLogo());
    }

    @When("logs in with valid username and password using datatable")
    public void loginWithValidCredentials(DataTable table) {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.pageIsLoaded(loginPage.getSignInButton());
        Map<String, String> loginData = table.asMap(String.class, String.class);
        String username = loginData.get("username");
        String password = loginData.get("password");
        loginPage.login(username, password);
    }

    @Then("the user is logged in successfully and can see the Home page")
    public void theUserIsLoggedInSuccessfullyAndCanSeeTheHomePage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.pageIsLoaded(homePage.getHomePageMenuLogo());
        homePage.checkIfUserIsLoggedIn();
    }

    @When("enters invalid {string} and {string} to login")
    public void entersInvalidUserNameAndPasswordToLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.pageIsLoaded(loginPage.getSignInButton());
        loginPage.login(username, password);
    }

    @Then("the login is failed, error invalid credentials is shown")
    public void failedLogin() {
        LoginPage failedLoginPage = new LoginPage(driver, wait);
        failedLoginPage.checkAlertBox();
        failedLoginPage.checkAlertErrorText();
    }

    @And("is on the MyProfile page")
    public void navigateToMyProfilePage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.openMyProfile();
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.checkProfilePage();
    }

    @When("updates the mobilephone number using datatable")
    public void updatesTheMobilePhoneNumber(DataTable table) {
        ProfilePage profilePage = new ProfilePage(driver, wait);
        Map<String, String> updateNumber = table.asMap(String.class, String.class);
        String mobilePhoneNumber = updateNumber.get("mobilePhoneNumber");
        profilePage.updateMobilePhoneData(mobilePhoneNumber);
    }

    @Then("update of the profile data is successful, success: Profile Updated Successfully.")
    public void updateOfTheProfileDataIsSuccessfulSuccessProfileUpdatedSuccessfully() {
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.checkUpdatedMobileData();
    }

    @When("updates the titleField")
    public void updatesTheTitleField(DataTable table) {
        ProfilePage profilePage = new ProfilePage(driver, wait);
        Map<String, String> updateNumber = table.asMap(String.class, String.class);
        String selectedOption = updateNumber.get("titleField");
        profilePage.updateTitleData(selectedOption);
    }

    @Then("successful title update, success: Profile Updated Successfully.")
    public void successfulTitleUpdateSuccessProfileUpdatedSuccessfully() {
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.checkUpdatedTtile();
    }

    @And("on the New Savings page")
    public void onTheNewSavingsPage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToSavingsMenu();
        SavingsPage savingsPage = new SavingsPage(driver, wait);
        savingsPage.pageIsLoaded(savingsPage.getSavingsPageTite());
    }

    @When("creates a new saving Account with <accountType>, <ownership>, <accountName> and <initialDeposit>")
    public void createsANewSavingAccountWithAccountTypeOwnershipAccountNameAndInitialDeposit(DataTable table) {
        SavingsPage savingsPage = new SavingsPage(driver, wait);
        Map<String, String> newSaving = table.asMap(String.class, String.class);
        String accountType = newSaving.get("accountType");
        String ownership = newSaving.get("ownership");
        String accountName = newSaving.get("accountName");
        String initialDeposit = newSaving.get("initialDeposit");
        savingsPage.startNewSaving(accountType, ownership, accountName, initialDeposit);
    }
}


