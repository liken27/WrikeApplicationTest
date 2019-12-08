import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "d:\\!!Projects\\WrikeApplicationTest\\driver\\geckodriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        wait = (new WebDriverWait(driver, 5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.wrike.com/");
        homePage = new HomePage(driver);
    }

    @Test
    public void checkIfPageHasChanged() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        // Executing methods
        homePage.clickGetStarted();
        homePage.inputRandomEmail();
        homePage.clickCreateButton();

        // Checking with assertion
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(), 'Help us provide you the best possible experience:')]")));
        Assert.assertEquals("https://www.wrike.com/resend/", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
