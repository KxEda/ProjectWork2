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

public class NewSavingsPage extends BasePage{

    @FindBy(xpath = "//h1[contains(text(),'Create Savings')]")
    WebElement savingsPageTite;

    @FindBy(xpath = "//label[@for='Savings']")
    WebElement savingsLabel;

    @FindBy(xpath = "//label[@for='Individual']")
    WebElement individualLabel;

    @FindBy(xpath = "//strong[contains(text(),'Account Name')]")
    WebElement accountNameLabel;

    @FindBy(xpath = "//input[@id='name']")
    WebElement accountNameField;

    @FindBy(xpath = "//strong[contains(text(),'Initial Deposit')]")
    WebElement initialDepositLabel;

    @FindBy(xpath = "//input[@id='openingBalance']")
    WebElement initialDepositField;

    @FindBy(xpath = "//button[@id='newSavingsSubmit']")
    WebElement savingsSubmitButton;

    @FindBy(xpath = "//span[contains(text(),'Error')]")
    WebElement alertErrorLabel;

    @FindBy(xpath = "//span[@id='new-account-error-msg']")
    WebElement errorText;

    public NewSavingsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public WebElement getSavingsPageTite() {
        return savingsPageTite;
    }

    public ViewSavingsPage startNewSaving(String accountType, String ownerShip, String accountName, String initialDeposit) {
        String xpathForSavings = String.format("//input[@type='radio' and @id='%s']",accountType);
        WebElement savingsRadio = driver.findElement(By.xpath(xpathForSavings));
        String xpathForIndividual = String.format("//input[@type='radio' and @id='%s']",ownerShip);
        WebElement individualRadio = driver.findElement(By.xpath(xpathForIndividual));
        wait.until(ExpectedConditions.visibilityOf(savingsLabel));
        wait.until(ExpectedConditions.elementToBeClickable(savingsRadio));
        savingsRadio.click();
        wait.until(ExpectedConditions.visibilityOf(individualLabel));
        wait.until(ExpectedConditions.elementToBeClickable(individualRadio));
        individualRadio.click();;
        wait.until(ExpectedConditions.visibilityOf(accountNameLabel));
        wait.until(ExpectedConditions.elementToBeClickable(accountNameField));
        accountNameField.click();
        accountNameField.clear();
        accountNameField.sendKeys(accountName);
        wait.until(ExpectedConditions.visibilityOf(initialDepositLabel));
        wait.until(ExpectedConditions.elementToBeClickable(initialDepositField));
        initialDepositField.click();
        initialDepositField.clear();
        initialDepositField.sendKeys(initialDeposit);
        wait.until(ExpectedConditions.elementToBeClickable(savingsSubmitButton));
        savingsSubmitButton.click();
        return  new ViewSavingsPage(driver, wait);
    }

    public void checkErrorText(String initialDeposit){
        assertTrue(savingsPageTite.isDisplayed());
        assertTrue(alertErrorLabel.isDisplayed());
        assertEquals("The initial deposit ($"+initialDeposit+".00) entered does not meet the minimum amount ($25.00) required. Please enter a valid deposit amount.", errorText.getText());
    }

}
