package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(xpath="//a[@id=\"home-menu-item\"]")
    private WebElement homePageMenuLogo;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
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
