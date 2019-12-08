import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        WebElement footerSocials = driver.findElement(By.xpath("//ul[@class='wg-footer__social-list']"));
        WebElement footerTwitterButton = driver.findElement(By.xpath("//ul[@class='wg-footer__social-list']/li[1]/a"));

        Assert.assertTrue("There's no Twitter button in footer", footerSocials.getAttribute("innerHTML").contains("href=\"https://twitter.com/wrike\" rel=\"dofollow\" target=\"_blank\""));
        Assert.assertTrue("Wrong URL on Twitter button", footerTwitterButton.getAttribute("href").equals("https://twitter.com/wrike"));
        Assert.assertTrue("Wrong icon on Twitter button", footerTwitterButton.getAttribute("innerHTML").contains("/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}