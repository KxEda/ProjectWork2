package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePage extends BasePage {

    @FindBy(xpath="//h1[contains(text(),'Profile')]")
    WebElement profileHeader;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }


    public void checkProfilePage(){
        assertTrue(profileHeader.isDisplayed());
    }
}
