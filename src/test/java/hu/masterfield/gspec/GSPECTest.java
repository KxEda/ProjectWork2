package hu.masterfield.gspec;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.reports.nodes.TestReportNode;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GSPECTest {
    private static final String[] MOBILE_TAGS = {"mobile"};
    private static final String[] DESKTOP_TAGS = {"desktop"};
    private static final String BASE_URL = "https://eng.digitalbank.masterfield.hu/bank/login";
    private static final String DIGITAL_BANK_LOGO_XPATH = "//img[@src='/bank/images/logo.png']";
    private static final String DIGITAL_BANK_LOGO_SRC = "https://eng.digitalbank.masterfield.hu/bank/images/logo.png";
    private static final String USER_NAME_LABEL_XPATH = "//strong[contains(text(),'User Name')]";
    private static final String USER_NAME_LABEL_TEXT ="USER NAME";
    private static final String PASSWORD_LABEL_XPATH = "//strong[contains(text(),'Password')]";
    private static final String PASSWORD_LABEL_TEXT="PASSWORD";
    private static final String LOGIN_FORM_BOX_XPATH="//div[@class='login-form']";
    private static final String LOGIN_FORM_BOX_CLASS="login-form";
    private static final String USERNAME_INPUT_FIELD_XPATH="//input[@id='username']";
    private static final String USERNAME_INPUT_FIELD_ID="username";
    private static final String PASSWORD_INPUT_FIELD_XPATH="//input[@id='password']";
    private static final String PASSWORD_INPUT_FIELD_ID="password";
    private static final String SIGN_IN_BUTTON_XPATH="//button[@id='submit']";
    private static final String SIGN_IN_BUTTON_ID="submit";
    private static final String SIGN_UP_HERE_XPATH="//a[contains(text(),'Sign Up Here!')]";
    private static final String SIGN_UP_HERE_TEXT="Sign Up Here!";
    private static final String SIGN_UP_HERE_LINK="https://eng.digitalbank.masterfield.hu/bank/signup";

    private LayoutReport layoutReport;
    private static WebDriverWait wait;

    @DisplayName("Check DigitalBank Login Page for desktop")
    public void testForDesktop(WebDriver driver) throws IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
        WebElement digitalBankLogo = wait.until(d -> d.findElement(By.xpath(DIGITAL_BANK_LOGO_XPATH)));
        WebElement loginFormBox = wait.until(d->d.findElement(By.xpath(LOGIN_FORM_BOX_XPATH)));
        WebElement userNameLabel = wait.until(d -> d.findElement(By.xpath(USER_NAME_LABEL_XPATH)));
        WebElement passwordLabel = wait.until(d -> d.findElement(By.xpath(PASSWORD_LABEL_XPATH)));
        WebElement userNameInput = wait.until(d->d.findElement(By.xpath(USERNAME_INPUT_FIELD_XPATH)));
        WebElement passwordInput = wait.until(d->d.findElement(By.xpath(PASSWORD_INPUT_FIELD_XPATH)));
        WebElement signInButton = wait.until(d->d.findElement(By.xpath(SIGN_IN_BUTTON_XPATH)));
        WebElement signUpHere = wait.until(d->d.findElement(By.xpath(SIGN_UP_HERE_XPATH)));

        assertTrue(digitalBankLogo.isDisplayed());
        assertEquals(DIGITAL_BANK_LOGO_SRC, digitalBankLogo.getAttribute("src"));

        assertTrue(loginFormBox.isDisplayed());
        assertEquals(LOGIN_FORM_BOX_CLASS, loginFormBox.getAttribute("class"));

        assertTrue(userNameLabel.isDisplayed());
        assertEquals(USER_NAME_LABEL_TEXT,userNameLabel.getText());

        assertTrue(passwordLabel.isDisplayed());
        assertEquals(PASSWORD_LABEL_TEXT, passwordLabel.getText());

        assertTrue(userNameInput.isDisplayed());
        assertEquals(USERNAME_INPUT_FIELD_ID, userNameInput.getAttribute("id"));

        assertTrue(passwordInput.isDisplayed());
        assertEquals(PASSWORD_INPUT_FIELD_ID, passwordInput.getAttribute("id"));

        assertTrue(signInButton.isDisplayed());
        assertEquals(SIGN_IN_BUTTON_ID,signInButton.getAttribute("id"));

        assertTrue(signUpHere.isDisplayed());
        assertEquals(SIGN_UP_HERE_TEXT,signUpHere.getText());
        assertEquals(SIGN_UP_HERE_LINK, signUpHere.getAttribute("href"));

        layoutReport = Galen.checkLayout(driver, "src/test/resources/gspecs/homePageLayout.gspec", Arrays.asList(DESKTOP_TAGS));

        final String REPORT_PATH = "target/galen-html-reports";
        List<GalenTestInfo> tests = new LinkedList<>();
        GalenTestInfo test = GalenTestInfo.fromString("DigitalBank login page ellenőrzése");
        test.getReport().layout(layoutReport, "Digitalbank login page elemeinek ellenőrzése");
        tests.add(test);

        new HtmlReportBuilder().build(tests, REPORT_PATH);

        for (GalenTestInfo i : tests) {
            for (TestReportNode n : i.getReport().getNodes()) {
                if (n.getStatus() == TestReportNode.Status.ERROR) {
                    fail("Teszt sikertelen");
                }
            }
        }
    }
}

