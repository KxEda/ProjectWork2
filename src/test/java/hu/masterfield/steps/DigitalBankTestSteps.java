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
    BasePage basePage = new BasePage(driver, wait);
    BannerPage bannerPage = new BannerPage(driver, wait);
    LoginPage loginPage = new LoginPage(driver, wait);
    HomePage homePage = new HomePage(driver, wait);
    ProfilePage profilePage = new ProfilePage(driver, wait);
    NewSavingsPage newSavingsPage = new NewSavingsPage(driver, wait);
    ViewSavingsPage viewSavingsPage = new ViewSavingsPage(driver, wait);

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
        driver.get(basePage.getBaseUrl());
        basePage.pageIsLoaded(basePage.getDigitalBankLogo());
    }

    @And("accepts cookies")
    public void acceptCookies() {
        bannerPage.pageIsLoaded(bannerPage.getCookieBanner());
        bannerPage.acceptCookies();
        bannerPage.checkIfCookiesBannerStillVisible();
        bannerPage.pageIsLoaded(bannerPage.getDigitalBankLogo());
    }

    @When("logs in with valid username and password using datatable")
    public void loginWithValidCredentials(DataTable table) {
        loginPage.pageIsLoaded(loginPage.getSignInButton());
        Map<String, String> loginData = table.asMap(String.class, String.class);
        String username = loginData.get("username");
        String password = loginData.get("password");
        loginPage.login(username, password);
    }

    @Then("the user is logged in successfully and can see the Home page")
    public void theUserIsLoggedInSuccessfullyAndCanSeeTheHomePage() {
        homePage.pageIsLoaded(homePage.getHomePageMenuLogo());
        homePage.checkIfUserIsLoggedIn();
    }

    @When("enters invalid {string} and {string} to login")
    public void entersInvalidUserNameAndPasswordToLogin(String username, String password) {
        loginPage.pageIsLoaded(loginPage.getSignInButton());
        loginPage.login(username, password);
    }

    @Then("the login is failed, error invalid credentials is shown")
    public void failedLogin() {
        loginPage.checkAlertBox();
        loginPage.checkAlertErrorText();
    }

    @And("is on the MyProfile page")
    public void navigateToMyProfilePage() {
        homePage.openMyProfile();
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.checkProfilePage();
    }

    @When("updates the mobilephone number using datatable")
    public void updatesTheMobilePhoneNumber(DataTable table) {
        Map<String, String> updateNumber = table.asMap(String.class, String.class);
        String mobilePhoneNumber = updateNumber.get("mobilePhoneNumber");
        profilePage.updateMobilePhoneData(mobilePhoneNumber);
    }

    @Then("update of the profile data is successful, success: Profile Updated Successfully.")
    public void updateOfTheProfileDataIsSuccessfulSuccessProfileUpdatedSuccessfully() {
        profilePage.checkUpdatedMobileData();
    }

    @When("updates the titleField")
    public void updatesTheTitleField(DataTable table) {
        Map<String, String> updateNumber = table.asMap(String.class, String.class);
        String selectedOption = updateNumber.get("titleField");
        profilePage.updateTitleData(selectedOption);
    }

    @Then("successful title update, success: Profile Updated Successfully.")
    public void successfulTitleUpdateSuccessProfileUpdatedSuccessfully() {
        profilePage.checkUpdatedTtile();
    }

    @And("on the New Savings page")
    public void onTheNewSavingsPage() {
        homePage.navigateToSavingsMenu();
        NewSavingsPage newSavingsPage = new NewSavingsPage(driver, wait);
        newSavingsPage.pageIsLoaded(newSavingsPage.getSavingsPageTite());
    }

    @When("new saving is started using {string}, {string}, {string}, {string}")
    public void createsANewSaving(String accountType, String ownership, String accountName, String initialDeposit) {
        newSavingsPage.pageIsLoaded(newSavingsPage.getSavingsPageTite());
        newSavingsPage.startNewSaving(accountType, ownership, accountName, initialDeposit);
    }

    @Then("new saving is created successfully")
    public void newSavingIsCreatedSuccessfully() {
        viewSavingsPage.checkIfSavingWasSuccessful();
    }

    @Then("new saving is failed, error: The {string} entered does not meet the minimum amount...")
    public void newSavingIsFailedErrorTheInitialDepositEnteredDoesNotMeetTheMinimumAmount(String initialDeposit) {
        newSavingsPage.checkErrorText(initialDeposit);
    }
}


