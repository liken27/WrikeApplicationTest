import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        // Setting variables
        WebElement getStartedButton = driver.findElement(By.xpath("//div[@class='r']//button"));
        WebElement trialInputField = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']/input"));
        WebElement trialCreateButton = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']/button"));
        String randomText = RandomStringUtils.randomAlphabetic(10);

        // Executing methods
        getStartedButton.click();
        randomText = randomText.concat("+wpt@wriketask.qaa");
        trialInputField.sendKeys(randomText);
        trialCreateButton.click();

        // Checking with assertion
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(), 'Help us provide you the best possible experience:')]")));
        Assert.assertEquals("https://www.wrike.com/resend/", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
