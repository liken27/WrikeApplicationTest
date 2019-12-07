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

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ResendPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
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
        resendPage = new ResendPage(driver);

        WebElement getStartedButton = driver.findElement(By.xpath("//div[@class='r']//button"));
        WebElement trialInputField = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']/input"));
        WebElement trialCreateButton = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']/button"));
        String randomText = RandomStringUtils.randomAlphabetic(10);

        getStartedButton.click();
        randomText = randomText.concat("+wpt@wriketask.qaa");
        trialInputField.sendKeys(randomText);
        trialCreateButton.click();
    }

    @Test
    public void checkResultsSubmit() {
        // Setting variables
        String randomText;
        int randomAnswer;
        String interestXpath = "//div[@data-code='interest_in_solution']/label";
        String teamXpath = "//div[@data-code='team_members']/label";
        String businessXpath = "//div[@data-code='primary_business']/label";
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='submit wg-btn wg-btn--navy js-survey-submit']"));
        WebElement surveySuccess = driver.findElement(By.xpath("//div[@class='survey-success']"));

        // Filling and submitting the form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-code='interest_in_solution']/label")));
        // Interest radio div
        List<WebElement> interest = driver.findElements(By.xpath(interestXpath));
        randomAnswer = (new Random().nextInt(interest.size())) + 1;
        interestXpath = interestXpath.concat("[" + randomAnswer + "]");
        driver.findElement(By.xpath(interestXpath)).click();

        // Team members radio div
        List<WebElement> team = driver.findElements(By.xpath(teamXpath));
        randomAnswer = (new Random().nextInt(team.size())) + 1;
        teamXpath = teamXpath.concat("[" + randomAnswer + "]");
        driver.findElement(By.xpath(teamXpath)).click();

        // Business radio div
        List<WebElement> business = driver.findElements(By.xpath(businessXpath));
        randomAnswer = (new Random().nextInt(business.size())) + 1;
        businessXpath = businessXpath.concat("[" + randomAnswer + "]");
        driver.findElement(By.xpath(businessXpath)).click();
        if (randomAnswer == 3) {
            randomText = RandomStringUtils.randomAlphabetic(20);
            businessXpath = businessXpath.concat("/button//input");
            driver.findElement(By.xpath(businessXpath)).sendKeys(randomText);
        }

        submitButton.click();

        // Checking with assertion
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='survey-success']")));
        if (surveySuccess.isDisplayed()) {
            Assert.assertEquals("survey-success", surveySuccess.getAttribute("class"));
        } else fail("Submit confirmation form has not appeared");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}