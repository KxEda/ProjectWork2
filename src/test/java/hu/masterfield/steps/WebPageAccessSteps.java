package hu.masterfield.steps;

import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class WebPageAccessSteps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @When("enters invalid {string} and {string} to login")
    public void entersInvalidUserNameAndPasswordToLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);
    }

    @Then("the login is failed, error invalid credentials is shown")
    public void failedLogin(){
        LoginPage failedLoginPage = new LoginPage(driver, wait);
        failedLoginPage.checkAlertBox();
        failedLoginPage.checkAlertErrorText();
    }



}
