package hu.masterfield.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private ChromeDriver driver;
    private WebDriverWait wait;


    @FindBy(xpath="//input[@id=\"username\"]")
    private WebElement userInput;

    @FindBy(xpath="//input[@id=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath="//button[@id=\"submit\"]")
    private WebElement submitButton;

    public LoginPage(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }


    public void login(String userName, String password){
        this.getUserInput().sendKeys(userName);
        this.getPasswordInput().sendKeys(password);
        this.getSubmitButton().click();
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
