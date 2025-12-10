package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPage{
    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(xpath="//*[@id='username']")
    private WebElement userInput;

    @FindBy(xpath="//*[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath="//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-danger alert-dismissible fade show']")
    private WebElement alertBox;

    @FindBy(xpath = "//span[contains(text(),'Error')]")
    private WebElement alertBoxErrorTitle;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void login(String userName, String password){
        wait.until(ExpectedConditions.elementToBeClickable(userInput));
        userInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public void checkAlertBox(){
        assertTrue(alertBox.isDisplayed());
    }

    public void checkAlertErrorText(){
        assertTrue(alertBoxErrorTitle.isDisplayed());
        String errorText ="Invalid credentials or access not granted due to user account status or an existing user session.";
        assertEquals(errorText, alertBoxErrorTitle.getText());
    }

    public WebElement getUserInput() {
        return userInput;
    }
}
