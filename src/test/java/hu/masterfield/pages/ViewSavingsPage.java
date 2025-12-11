package hu.masterfield.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewSavingsPage extends BasePage{

    @FindBy(xpath = "//h1[contains(text(),'View Savings')]")
    WebElement viewSavingsPageTitle;

    @FindBy(xpath = "//span[contains(text(),'Confirmation')]")
    WebElement alertConfirmationLabel;

    @FindBy(xpath = "//span[@id='new-account-msg']")
    WebElement confirmationText;

    public ViewSavingsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public void checkIfSavingWasSuccessful(){
        assertTrue(viewSavingsPageTitle.isDisplayed());
        assertTrue(alertConfirmationLabel.isDisplayed());
        assertEquals("Successfully created new Savings account named TestAccount", confirmationText.getText());
    }
}
