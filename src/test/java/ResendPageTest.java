import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ResendPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private ResendPage resendPage;

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
        resendPage = new ResendPage(driver);

        // Navigating to the resend page
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickGetStarted();
        homePage.inputRandomEmail();
        homePage.clickCreateButton();
    }

    @Test
    public void checkResultsSubmit() {
        By interestSolution = By.xpath("//div[@data-code='interest_in_solution']/label");
        By surveySuccess = By.xpath("//div[@class='survey-success']");
        WebElement surveySuccessElem = driver.findElement(surveySuccess);
        ResendPage resendPage = PageFactory.initElements(driver, ResendPage.class);
        // Executing methods
        wait.until(ExpectedConditions.visibilityOfElementLocated(interestSolution));
        resendPage.fillQA();

        // Checking with assertion
        wait.until(ExpectedConditions.visibilityOfElementLocated(surveySuccess));
        if (surveySuccessElem.isDisplayed()) {
            Assert.assertEquals("survey-success", surveySuccessElem.getAttribute("class"));
        } else fail("Submit confirmation form has not appeared");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}