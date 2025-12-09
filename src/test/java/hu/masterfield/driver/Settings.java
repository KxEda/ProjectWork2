package hu.masterfield.driver;

public class Settings {
    private static final String BASE_URL = "https://eng.digitalbank.masterfield.hu/bank/login";
    private static final String COOKIE_OK_BTN_XPATH = "//button[contains(text(),'OK')]";
    private static final String DIGITALBANK_LOGO_XPATH ="//img[@src=\"/bank/images/logo.png\"]";

    public static String getBaseUrl(){
        return BASE_URL;
    }

    public static String getCookieOkBtnXpath(){
        return COOKIE_OK_BTN_XPATH;
    }

    public static String getDigitalbankLogoXpath(){
        return DIGITALBANK_LOGO_XPATH;
    }

}
