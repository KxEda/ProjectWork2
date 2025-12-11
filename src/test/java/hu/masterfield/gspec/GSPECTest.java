package hu.masterfield.gspec;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.reports.nodes.TestReportNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GSPECTest {
    private static final String[] MOBILE_TAGS = {"mobile"};
    private static final String[] DESKTOP_TAGS = {"desktop"};
    private static final String BASE_URL = "https://eng.digitalbank.masterfield.hu/bank/login";
    private static final String DIGITAL_BANK_LOGO_XPATH = "//img[@src='/bank/images/logo.png']";
    private static final String USER_NAME_LABEL_XPATH = "//strong[contains(text(),'User Name')]";
    private static final String PASSWORD_LABEL_XPATH = "//strong[contains(text(),'Password')]";
    private LayoutReport layoutReport;

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public static void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Check DigitalBank Login Page for desktop")
    public void testForDesktop() throws IOException {
        driver.get(BASE_URL);
        WebElement digitalBankLogo = wait.until(d -> d.findElement(By.xpath(DIGITAL_BANK_LOGO_XPATH)));
        WebElement userNameLabel = wait.until(d -> d.findElement(By.xpath(USER_NAME_LABEL_XPATH)));
        WebElement passwordLabel = wait.until(d -> d.findElement(By.xpath(PASSWORD_LABEL_XPATH)));

        assertEquals("/bank/images/logo.png", digitalBankLogo.getAttribute("src"));

        // Galen layout ellenőrzés

        layoutReport = Galen.checkLayout(driver, "src/test/resources/gspecs/homePageLayout.gspec", Arrays.asList(DESKTOP_TAGS));

        // Riport generálása

        final String REPORT_PATH = "target/galen-html-reports";
        List<GalenTestInfo> tests = new LinkedList<>();
        GalenTestInfo test = GalenTestInfo.fromString("DigitalBank login page ellenőrzése");
        test.getReport().layout(layoutReport, "Digitalbank login page elemeinek ellenőrzése");
        tests.add(test);

        new HtmlReportBuilder().build(tests, REPORT_PATH);

        // Ha bármelyik sazbályunk nem teljesül, akkor bukjon el a tesztünk

        for (GalenTestInfo i : tests) {
            for (TestReportNode n : i.getReport().getNodes()) {
                if (n.getStatus() == TestReportNode.Status.ERROR) {
                    fail("Teszt sikertelen");
                }
            }
        }
    }
}

