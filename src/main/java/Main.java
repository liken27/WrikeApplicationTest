import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {

    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "d:\\!!Projects\\WrikeApplicationTest\\driver\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.wrike.com/");

        HomePage homePage = new HomePage(driver);
        ResendPage resendPage = new ResendPage(driver);

        homePage.clickGetStarted();
        homePage.inputRandomEmail();
        homePage.clickCreateButton();
        resendPage.fillQA();
        resendPage.checkFooterTwitter();
    }
}
