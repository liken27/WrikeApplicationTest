import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FooterSectionTest {

    private WebDriver driver;
    private FooterSection footerSection;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "d:\\!!Projects\\WrikeApplicationTest\\driver\\geckodriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.wrike.com/");
        footerSection = new FooterSection(driver);
    }

    @Test
    public void checkTwitterFooter() {
        FooterSection footerSection = PageFactory.initElements(driver, FooterSection.class);

        Assert.assertTrue("There's no Twitter button in footer", footerSection.containsTwitter());
        Assert.assertTrue("Wrong URL on Twitter button", footerSection.linkTwitterIsValid());
        Assert.assertTrue("Wrong icon on Twitter button", footerSection.iconTwitterIsValid());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}