package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final String BASE_URL = "https://eng.digitalbank.masterfield.hu/bank/login";

    @FindBy(xpath="//img[@src='/bank/images/logo.png']")
    WebElement digitalBankLogo;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    public String getBaseUrl(){
        return BASE_URL;
    }

    public void pageIsLoaded(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        assertTrue(element.isDisplayed());
    }

    public WebElement getDigitalBankLogo() {
        return digitalBankLogo;
    }
}
