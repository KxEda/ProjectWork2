package hu.masterfield.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BannerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath="//div[@id=\"freeprivacypolicy-com---nb\"]")
    private WebElement cookieBanner;

    @FindBy(xpath="//button[contains(text(),'OK')]")
    private WebElement cookieOKButton;

    @FindBy(xpath="//img[@src=\"/bank/images/logo.png\"]")
    private WebElement loginPageDigitalBankLogo;

    public BannerPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    public void checkIfCookieBannerIsPresent(){
        assertTrue(cookieBanner.isDisplayed());
    }

    public void acceptCookies(){
        wait.until(ExpectedConditions.visibilityOf(cookieOKButton));
        if (cookieOKButton.isDisplayed()) {
            cookieOKButton.click();
        }
    }

    public void checkLoginPageAfterCookies(){
        assertFalse(cookieBanner.isDisplayed());
        assertTrue(loginPageDigitalBankLogo.isDisplayed());
    }


    public WebElement getCookieBanner() {
        return cookieBanner;
    }

    public WebElement getCookieOKButton() {
        return cookieOKButton;
    }
}
