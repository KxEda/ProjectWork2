package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath="//h1[contains(text(),'Profile')]")
    WebElement profileHeader;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void checkProfilePage(){
        assertTrue(profileHeader.isDisplayed());
    }
}
