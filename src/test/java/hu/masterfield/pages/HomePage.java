package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath="//a[@id=\"home-menu-item\"]")
    private WebElement homePageMenuLogo;


    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver,this);
    }

    public void checkIfUserIsLoggedIn(){
        wait.until(ExpectedConditions.visibilityOf(homePageMenuLogo));
        assertTrue(homePageMenuLogo.isDisplayed());
        assertEquals("Home", homePageMenuLogo.getText());
    }

    public WebElement getHomePageMenuLogo() {

        return homePageMenuLogo;
    }
}
