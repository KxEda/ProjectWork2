package hu.masterfield;

import hu.masterfield.driver.Settings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BaseTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static WebElement cookieOKBtn;
    private static WebElement digitalBankLogo;

    @BeforeAll
    public static void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(Settings.getBaseUrl());
        cookieOKBtn = driver.findElement(By.xpath(Settings.getCookieOkBtnXpath()));
        if(cookieOKBtn.isDisplayed()) {
            cookieOKBtn.click();
        }
        digitalBankLogo = driver.findElement(By.xpath(Settings.getDigitalbankLogoXpath()));
        assertTrue(digitalBankLogo.isDisplayed());
    }

//    @AfterAll
//    public static void tearDown(){
//        if(driver!=null){
//            driver.close();
//        }
//    }


    public static WebDriver getDriver() {
        return driver;
    }
}
