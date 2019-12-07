import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Main {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "d:\\!!Projects\\WrikeApplicationTest\\driver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "d:\\!!Projects\\WrikeApplicationTest\\driver\\chromedriver.exe");

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.wrike.com/");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        ResendPage resendPage = PageFactory.initElements(driver, ResendPage.class);
        FooterSection footerSection = PageFactory.initElements(driver, FooterSection.class);

        homePage.clickGetStarted();
        homePage.inputRandomEmail();
        homePage.clickCreateButton();
        resendPage.fillQA();
        footerSection.checkFooterTwitter();

        driver.quit();
    }
}
