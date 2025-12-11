package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage extends BasePage{

    @FindBy(xpath="//a[@id=\"home-menu-item\"]")
    private WebElement homePageMenuLogo;

    @FindBy(xpath = "//img[@class='user-avatar rounded-circle']")
    private WebElement myProfileIcon;

    @FindBy(xpath = "//a[@href='/bank/user/profile']")
    private WebElement myProfileSelector;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public void checkIfUserIsLoggedIn(){
        wait.until(ExpectedConditions.visibilityOf(homePageMenuLogo));
        assertTrue(homePageMenuLogo.isDisplayed());
        assertEquals("Home", homePageMenuLogo.getText());
    }

    public ProfilePage openMyProfile(){
        wait.until(ExpectedConditions.elementToBeClickable(myProfileIcon));
        myProfileIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(myProfileSelector));
        myProfileSelector.click();
        return new ProfilePage(driver, wait);
    }

}
