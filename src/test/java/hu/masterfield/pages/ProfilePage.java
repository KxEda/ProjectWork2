package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePage extends BasePage {

    @FindBy(xpath="//h1[contains(text(),'Profile')]")
    WebElement profileHeader;

    @FindBy(xpath = "//strong[contains(text(),'Mobile Phone')]")
    WebElement mobilePhoneNumberTitle;

    @FindBy(xpath = "//input[@id='mobilePhone']")
    WebElement mobilePhoneInputBox;

    @FindBy(xpath = "//div[@class='card-footer']//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//strong[contains(text(),'Title')]")
    WebElement titleTitle;

    @FindBy(xpath = "//select[@id='title']")
    WebElement titleDropdown;

    @FindBy(xpath = "//option[contains(text(),'Mrs.')]")
    WebElement chooseMrsOption;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement alertBox;

    @FindBy(xpath = "//span[@class='badge badge-pill badge-success']")
    WebElement alertBoxSuccessTitle;

    @FindBy(xpath = "//span[contains(text(),'Profile Updated Successfully.')]")
    WebElement alertBoxText;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public void checkProfilePage(){
        assertTrue(profileHeader.isDisplayed());
    }

    public void updateMobilePhoneData(String mobilePhoneNumber){
        assertTrue(mobilePhoneNumberTitle.isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(mobilePhoneInputBox));
        mobilePhoneInputBox.click();
        mobilePhoneInputBox.clear();
        mobilePhoneInputBox.sendKeys(mobilePhoneNumber);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void checkUpdatedMobileData(){
        String newNumber = "123-366-3123";
        assertEquals(newNumber, mobilePhoneInputBox.getAttribute("value"));
        assertTrue(alertBoxSuccessTitle.isDisplayed());
        assertEquals("Profile Updated Successfully.", alertBoxText.getText());
    }

    public void updateTitleData(String titleData){
        assertTrue(titleTitle.isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(titleDropdown));
        titleDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseMrsOption));
        chooseMrsOption.click();
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void checkUpdatedTtile(){
        String newTitle = "Mrs.";
        assertEquals(newTitle, chooseMrsOption.getText());
        assertTrue(alertBoxSuccessTitle.isDisplayed());
        assertEquals("Profile Updated Successfully.", alertBoxText.getText());
    }
}
