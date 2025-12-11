package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SavingsPage extends BasePage{

    @FindBy(xpath = "//h1[contains(text(),'Create Savings')]")
    WebElement savingsPageTite;

    public SavingsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public WebElement getSavingsPageTite() {
        return savingsPageTite;
    }

    public void startNewSaving(String accountType, String ownerShip, String accountName, String initialDeposit) {
    }
}
