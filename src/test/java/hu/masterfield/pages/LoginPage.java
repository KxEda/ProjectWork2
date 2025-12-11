package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPage extends BasePage{

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
    
    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
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
        System.out.println(alertBox.getText());
        String text = alertBox.getText();
        List<String> listOfErrorMessageParts = getStringsOfErrorMessage(text);
        String errorMessage = String.join(" ",listOfErrorMessageParts).trim();
        assertEquals(errorText, errorMessage);
    }

    private static List<String> getStringsOfErrorMessage(String text) {
        String[] stringParts = text.split(" ");
        String[] errorMessageParts = Arrays.copyOfRange(stringParts,1,stringParts.length);
        String lastElementOfErrorMessage = errorMessageParts[errorMessageParts.length-1];
        String[] lastElementOfErrorMessageParts = lastElementOfErrorMessage.split("\n");
        List<String> listOfErrorMessageParts = new ArrayList<>((Arrays.asList(errorMessageParts)));
        listOfErrorMessageParts.remove(lastElementOfErrorMessage);
        listOfErrorMessageParts.add(lastElementOfErrorMessageParts[0]);
        return listOfErrorMessageParts;
    }

    public WebElement getUserInput() {
        return userInput;
    }
}
