package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage{
    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(xpath="//input[@id=\"username\"]")
    private WebElement userInput;

    @FindBy(xpath="//input[@id=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath="//button[@id=\"submit\"]")
    private WebElement submitButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public HomePage login(String userName, String password){
        userInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new HomePage(driver, wait);
    }

    public WebElement getUserInput() {
        return userInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }
}
